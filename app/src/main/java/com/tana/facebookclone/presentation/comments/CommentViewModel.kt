package com.tana.facebookclone.presentation.comments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tana.facebookclone.domain.use_cases.comments.add_comment.AddCommentUseCase
import com.tana.facebookclone.domain.use_cases.comments.get_comments.GetCommentsUseCase
import com.tana.facebookclone.domain.use_cases.get_user_use_case.GetUserUseCase
import com.tana.facebookclone.utils.AppEvents
import com.tana.facebookclone.utils.Constants
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
class CommentViewModel @Inject constructor(
    private val addCommentUseCase: AddCommentUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val getUserUseCase: GetUserUseCase,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CommentsUiState())
    val uiState = _uiState.asStateFlow()
    private val _appEvents = Channel<AppEvents>()
    val appEvents = _appEvents.receiveAsFlow()

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                loading = true
            )

            savedStateHandle.get<String>(Constants.PARAM_POST_ID)?.let { postId ->
                getCommentsUseCase(postId = postId).collectLatest { response ->
                    when(response) {
                        is Resource.Success -> {
                            _uiState.value = _uiState.value.copy(
                                comments = response.data
                            )
                        }
                        is Resource.Failure -> {
                            _uiState.value = _uiState.value.copy(
                                errorMessage = response.message
                            )
                        }
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(
                                loading = true
                            )
                        }
                    }
                }
            }

            _uiState.value = _uiState.value.copy(
                loading = false
            )
        }
    }

    init {
        viewModelScope.launch {
            getUserUseCase().collectLatest { response ->
                when(response) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(user = response.data)
                    }
                    is Resource.Failure -> Unit
                    is Resource.Loading -> Unit
                }
            }
        }
    }

    fun addComment() {
        viewModelScope.launch {
            savedStateHandle.get<String>(Constants.PARAM_POST_ID)?.let { postId ->
                addCommentUseCase(comment = _uiState.value.comment, postId = postId).collectLatest { response ->
                    when(response) {
                        is Resource.Success -> {
                            _appEvents.send(AppEvents.ShowSnackBar(response.data.message))
                            _uiState.value = _uiState.value.copy(comment = "")
                        }
                        is Resource.Failure -> {
                            _appEvents.send(AppEvents.ShowSnackBar(response.message ?: ""))
                        }
                        is Resource.Loading -> Unit
                    }
                }
            }
        }
    }

    fun onCommentChange(comment: String) {
        _uiState.value = _uiState.value.copy(
            comment = comment
        )
    }

}