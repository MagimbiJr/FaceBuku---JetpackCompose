package com.tana.facebookclone.presentation.edit_profile

import android.net.Uri
import com.tana.facebookclone.domain.modal.User

data class EditProfileUiState(
    val loading: Boolean = false,
    val user: User? = null,
    val profileUri: Uri? = null,
    val coverUri: Uri? = null,
    val errorMessage: String = ""
)
