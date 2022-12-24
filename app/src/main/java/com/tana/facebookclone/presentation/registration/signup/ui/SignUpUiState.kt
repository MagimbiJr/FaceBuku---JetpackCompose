package com.tana.facebookclone.presentation.registration.signup.ui

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class SignUpUiState(
    val loading: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val verifyPassword: String = "",
    val gender: String = "",
    val birthDate: LocalDate = LocalDate.now(),
    val errorMessage: String = "",
)
