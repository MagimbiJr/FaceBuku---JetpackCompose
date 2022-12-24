package com.tana.facebookclone.presentation.add_post

import android.net.Uri
import com.tana.facebookclone.domain.modal.User

data class AddPostUiState(
    val loading: Boolean = false,
    val caption: String = "",
    val uri: Uri? = null,
    val user: User? = null,
    val errorMessage: String? = null
)
