package com.tana.facebookclone.presentation.profile

import android.net.Uri
import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.domain.modal.Post
import com.tana.facebookclone.domain.modal.User

data class ProfileUiState(
    val loading: Boolean = false,
    val user: User?  = null,
    val uri: Uri? = null,
    val posts: List<Post> = emptyList(),
    val comments: List<Comment> = emptyList(),
    val errorMessage: String = ""
)
