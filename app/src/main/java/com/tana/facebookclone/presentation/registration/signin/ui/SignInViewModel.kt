package com.tana.facebookclone.presentation.registration.signin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tana.facebookclone.domain.use_cases.auth_use_cases.SignInUseCase
import com.tana.facebookclone.utils.AppEvents
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
class SignInViewModel @Inject constructor(
    private val useCase: SignInUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()
    private val _appEvent = Channel<AppEvents>()
    val appEvent = _appEvent.receiveAsFlow()
    var isLoginFailed = false

    fun phoneOrEmailChange(phoneOrEmail: String) {
        _uiState.value = _uiState.value.copy(phoneOrEmail = phoneOrEmail)
    }

    fun passwordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login() {
       viewModelScope.launch {
           
           _uiState.value = _uiState.value.copy(
               loading = true
           )
           useCase(
               phoneOrEmail = _uiState.value.phoneOrEmail,
               password = _uiState.value.password
           ).collectLatest { response ->
               when(response) {
                   is Resource.Success -> {
                       _appEvent.send(AppEvents.ShowSnackBar(message = response.data.message))
                       _appEvent.send(AppEvents.Navigate("home"))
                   }
                   is Resource.Failure -> {
                       _uiState.value = _uiState.value.copy(
                           errorMessage = response.message,
                           loading = false,
                           password = "",
                       )
                       isLoginFailed = true
                   }
                   is Resource.Loading -> {
                       _uiState.value = _uiState.value.copy(loading = true)
                   }
               }
           }
           _uiState.value = _uiState.value.copy(loading = false)
       }
    }

    fun navigateToSignUp() {
        viewModelScope.launch {
            _appEvent.send(AppEvents.Navigate("sign_up"))
        }
    }
}