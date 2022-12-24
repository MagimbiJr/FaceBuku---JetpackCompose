package com.tana.facebookclone.presentation.registration.signin.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.facebookclone.presentation.components.LoadingScreen
import com.tana.facebookclone.presentation.theme.Purple700
import com.tana.facebookclone.utils.AppEvents
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignInScreen(
    scaffoldState: ScaffoldState,
    navigateToHome: (AppEvents.Navigate) -> Unit,
    navigateToSignUp: (AppEvents.Navigate) -> Unit,
    systemUiController: SystemUiController,
    focusManager: FocusManager,
    keyboardState: Keyboard,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val appEvents = viewModel.appEvent

    if (keyboardState.name == Keyboard.Closed.name) {
        systemUiController.setStatusBarColor(Purple700)
    } else {
        systemUiController.setStatusBarColor(MaterialTheme.colors.surface)
    }

    LaunchedEffect(key1 = appEvents) {
        appEvents.collectLatest { event ->
            when(event) {
                is AppEvents.Navigate -> {
                    navigateToHome(event)
                    navigateToSignUp(event)
                }
                is AppEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is AppEvents.PopBack -> Unit
                is AppEvents.SignInRequired -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        if (uiState.loading) {
            LoadingScreen()
        } else {
            SignInContent(
                focusManager = focusManager,
                modifier = modifier
                    .padding(it),
                onSignInClick = viewModel::login,
                onSignUpClick = viewModel::navigateToSignUp,
                onForgetButtonClick = { /*TODO*/ },
                onPhoneOrEmailChange = viewModel::phoneOrEmailChange,
                onPasswordChange = viewModel::passwordChange,
                uiState = uiState,
                keyboardState = keyboardState,
                viewModel = viewModel
            )
        }
    }
}