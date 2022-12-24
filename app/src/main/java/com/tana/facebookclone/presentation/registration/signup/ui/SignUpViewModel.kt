package com.tana.facebookclone.presentation.registration.signup.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tana.facebookclone.domain.use_cases.auth_use_cases.SignUpUseCase
import com.tana.facebookclone.utils.AppEvents
import com.tana.facebookclone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())

    @RequiresApi(Build.VERSION_CODES.O)
    val uiState = _uiState.asStateFlow()
    private val _appEvent = Channel<AppEvents>()
    val appEvent = _appEvent.receiveAsFlow()
    var isSignUpFailed = false


    fun signUp() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                loading = true
            )
            signUpUseCase(
                firstName = _uiState.value.firstName,
                lastName = _uiState.value.lastName,
                email = _uiState.value.email,
                password = _uiState.value.password,
                birthDate = _uiState.value.birthDate.toString(),
                gender = _uiState.value.gender,
            ).collectLatest { response ->
                when (response) {
                    is Resource.Success -> {
                        _appEvent.send(AppEvents.ShowSnackBar(message = response.data.message))
                        _appEvent.send(AppEvents.Navigate("home"))
                    }
                    is Resource.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = response.message ?: "",
                            loading = false,
                            password = "",
                        )
                        isSignUpFailed = true
                    }
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }
                }
            }
            _uiState.value = _uiState.value.copy(loading = false)
        }
    }

    fun firstNameChange(firstName: String) {
        _uiState.value = _uiState.value.copy(firstName = firstName)
    }

    fun lastNameChange(lastName: String) {
        _uiState.value = _uiState.value.copy(lastName = lastName)
    }

    fun emailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun passwordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun verifyPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(verifyPassword = password)
    }

    fun birthDateChange(date: LocalDate) {
        _uiState.value = _uiState.value.copy(birthDate = date)
    }

    fun onGenderChange(gender: String) {
        _uiState.value = _uiState.value.copy(gender = gender)
    }

    fun navigateToSignIn() {
        viewModelScope.launch {
            _appEvent.send(AppEvents.Navigate("signin"))
        }
    }

}