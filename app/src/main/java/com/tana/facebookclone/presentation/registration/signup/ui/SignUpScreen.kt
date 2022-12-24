package com.tana.facebookclone.presentation.registration.signup.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.facebookclone.presentation.components.LoadingScreen
import com.tana.facebookclone.presentation.registration.signin.ui.Keyboard
import com.tana.facebookclone.presentation.theme.Purple700
import com.tana.facebookclone.utils.AppEvents
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignUpScreen(
    navigateToHome: (AppEvents.Navigate) -> Unit,
    navigateToSignIn: (AppEvents.Navigate) -> Unit,
    focusManager: FocusManager,
    keyboardState: Keyboard,
    scaffoldState: ScaffoldState,
    scrollState: ScrollState,
    systemUiController: SystemUiController,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    if (keyboardState.name == Keyboard.Closed.name) {
        systemUiController.setStatusBarColor(Purple700)
    } else {
        systemUiController.setStatusBarColor(MaterialTheme.colors.surface)
    }

    LaunchedEffect(key1 = viewModel.appEvent) {
        viewModel.appEvent.collectLatest { event ->
            when(event) {
                is AppEvents.Navigate -> {
                    navigateToHome(event)
                    navigateToSignIn(event)
                }
                is AppEvents.PopBack -> Unit
                is AppEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is AppEvents.SignInRequired -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->
        if (uiState.loading) {
            Log.d("TAG", "SignUpScreen: loading is ${uiState.loading}")
            LoadingScreen()
        } else {
            Log.d("TAG", "SignUpScreen: main ui rendered")
            SignUpContent(
                uiState = uiState,
                focusManager = focusManager,
                modifier = modifier.padding(paddingValues),
                scrollState = scrollState,
                onSignInClick = viewModel::navigateToSignIn,
                onSignUpClick = viewModel::signUp,
                onFirstNameChange = viewModel::firstNameChange,
                onLastNameChange = viewModel::lastNameChange,
                onEmailChange = viewModel::emailChange,
                onPasswordChange = viewModel::passwordChange,
                onVerifyPassword = viewModel::verifyPasswordChange,
                onBirthDateChange = viewModel::birthDateChange,
                onGenderChange = viewModel::onGenderChange,
                keyboardState = keyboardState,
                viewModel = viewModel
            )
        }
    }
}