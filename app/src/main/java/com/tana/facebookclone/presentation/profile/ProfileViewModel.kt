package com.tana.facebookclone.presentation.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tana.facebookclone.domain.use_cases.add_post_use_case.UpdateCoverUseCase
import com.tana.facebookclone.domain.use_cases.comments.get_comments.GetCommentsUseCase
import com.tana.facebookclone.domain.use_cases.get_posts.GetPostsByUserUseCase
import com.tana.facebookclone.domain.use_cases.get_user_use_case.GetUserUseCase
import com.tana.facebookclone.utils.AppEvents
import com.tana.facebookclone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase,
    private val getPostsByUserUseCase: GetPostsByUserUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val updateCoverUseCase: UpdateCoverUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()
    private val _appEvents = Channel<AppEvents>()
    val appEvents = _appEvents.receiveAsFlow()

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = _uiState.value.user == null)
            userUseCase().collectLatest { response ->
                when (response) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(user = response.data)
                    }
                    is Resource.Failure -> {
                        _uiState.value = _uiState.value.copy(errorMessage = response.message ?: "")
                    }
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }
                }
            }
            _uiState.value = _uiState.value.copy(loading = false)
        }
    }

    init {
        viewModelScope.launch {
            Log.d("TAG", "ViewModel: user is ${uiState.value.user?.firstName}")
            Log.d("TAG", "ViewModel: Id is ${uiState.value.user?.userId}")
            val user = _uiState.value.user
            if (user != null) {
                getPostsByUserUseCase(userId = user.userId).collectLatest { response ->
                    when (response) {
                        is Resource.Success -> {
                            val posts = response.data.reversed()
                            _uiState.value = _uiState.value.copy(
                                posts = posts
                            )
                        }
                        is Resource.Failure -> {
                            _uiState.value = _uiState.value.copy(
                                errorMessage = response.message!!
                            )
                        }
                        is Resource.Loading -> Unit
                    }
                }
            } else {
                _appEvents.send(AppEvents.SignInRequired("signin"))
            }
        }
    }

    init {
        viewModelScope.launch {
            _uiState.value.posts.forEach { post ->
                getCommentsUseCase(postId = post.postId).collectLatest { response ->
                    when (response) {
                        is Resource.Success -> {
                            _uiState.value = _uiState.value.copy(comments = response.data)
                        }
                        is Resource.Failure -> Unit
                        is Resource.Loading -> Unit
                    }
                }
            }
        }
    }

    fun updateCoverPhoto() {
        viewModelScope.launch {
           if (_uiState.value.user != null) {
               _uiState.value = _uiState.value.copy(loading = true)
               updateCoverUseCase(
                   uri = _uiState.value.uri!!,
                   user = _uiState.value.user
               ).collectLatest { response ->
                   when(response) {
                       is Resource.Success -> {
                           _appEvents.send(AppEvents.ShowSnackBar(response.data.message))
                           _appEvents.send(AppEvents.Navigate("home"))
                       }
                       is Resource.Failure -> {
                           _uiState.value = _uiState.value.copy(errorMessage = response.message ?: "")
                       }
                       is Resource.Loading -> {
                           _uiState.value = _uiState.value.copy(loading = true)
                       }
                   }
               }
               _uiState.value = _uiState.value.copy(loading = false)
           } else {
               _appEvents.send(AppEvents.SignInRequired("signin"))
           }
        }
    }

    fun updateUri(uri: Uri?) {
        _uiState.value = _uiState.value.copy(uri = uri)
    }

    fun changeDpClicked() {}

    fun changeCoverClicked() {}

    fun createCoverClicked() {
        viewModelScope.launch {
            _appEvents.send(
                AppEvents.Navigate(route = "update_cover_screen")
            )
        }
    }

    fun backIconClicked() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.PopBack)
        }
    }

    fun createAvatarClicked() {
        viewModelScope.launch {
            _appEvents.send(
                AppEvents.ShowSnackBar(
                    message = "Avatar creation is unavailable"
                )
            )
        }
    }

}