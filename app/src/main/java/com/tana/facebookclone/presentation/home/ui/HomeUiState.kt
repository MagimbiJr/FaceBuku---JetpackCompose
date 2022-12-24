package com.tana.facebookclone.presentation.home.ui

import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.domain.modal.Post
import com.tana.facebookclone.domain.modal.User

data class HomeUiState(
    val loading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val comments: List<Comment> = emptyList(),
    val user: User? = null,
    val errorMessage: String? = null
)
