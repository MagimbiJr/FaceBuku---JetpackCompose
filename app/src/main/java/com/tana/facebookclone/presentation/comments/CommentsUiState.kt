package com.tana.facebookclone.presentation.comments

import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.domain.modal.User

data class CommentsUiState(
    val loading: Boolean = false,
    val comment: String = "",
    val comments: List<Comment> = emptyList(),
    val user: User? = null,
    val errorMessage: String? = null,
)
