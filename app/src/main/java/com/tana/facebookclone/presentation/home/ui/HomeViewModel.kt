package com.tana.facebookclone.presentation.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.tana.facebookclone.domain.modal.Post
import com.tana.facebookclone.domain.use_cases.comments.get_comments.GetCommentsUseCase
import com.tana.facebookclone.domain.use_cases.get_posts.GetPostsUseCase
import com.tana.facebookclone.domain.use_cases.get_user_use_case.GetUserUseCase
import com.tana.facebookclone.utils.AppEvents
import com.tana.facebookclone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetPostsUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    private val _appEvents = Channel<AppEvents>()
    val appEvents = _appEvents.receiveAsFlow()


    init {

        viewModelScope.launch {
                getUserUseCase().collectLatest { response ->
                    when(response) {
                        is Resource.Success -> {
                            _uiState.value = _uiState.value.copy(user = response.data)
                        }
                        is Resource.Failure -> {
                            _uiState.value = _uiState.value.copy(errorMessage = response.message)
                        }
                        is Resource.Loading -> Unit
                    }
                }
        }

    }

    init {
        viewModelScope.launch {
            if (FirebaseAuth.getInstance().currentUser != null) {

                _uiState.value = _uiState.value.copy(
                    loading = _uiState.value.user == null
                )
                useCase().collect { response ->
                    when(response) {
                        is Resource.Success -> {
                            val posts = response.data.reversed()
                            _uiState.value = _uiState.value.copy(
                                posts = posts,
                                loading = false
                            )
                        }
                        is Resource.Failure -> {
                            _uiState.value = _uiState.value.copy(
                                errorMessage = response.message,
                                loading = false
                            )

                        }
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                loading = _uiState.value.posts == emptyList<Post>()
                            )
                        }
                    }
                }
            } else {
                _appEvents.send(AppEvents.SignInRequired("signin"))

            }
            _uiState.value = _uiState.value.copy(
                loading = false
            )
        }
    }

    init {
        viewModelScope.launch {
            _uiState.value.posts.forEach { post ->
                getCommentsUseCase(post.postId).collect { response ->
                    when(response) {
                        is Resource.Success -> {
                            _uiState.value = _uiState.value.copy(comments = response.data)
                            Log.d("TAG", "postInit: comments amount is ${_uiState.value.comments}")
                        }
                        is Resource.Failure -> Unit
                        is Resource.Loading -> Unit
                    }
                }
            }
        }
    }


}