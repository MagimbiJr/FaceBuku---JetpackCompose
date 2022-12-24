package com.tana.facebookclone.presentation.registration.signin.ui

data class SignInUiState(
    val loading: Boolean = false,
    val phoneOrEmail: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    //val isLoginFailed: Boolean = false
)
