package com.tana.facebookclone.utils

sealed class AppEvents {
    data class SignInRequired(val route: String) : AppEvents()
    data class ShowSnackBar(val message: String) : AppEvents()
    data class Navigate(val route: String) : AppEvents()
    object PopBack : AppEvents()
}
