package com.tana.facebookclone.presentation.registration.signin.ui

import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.Rect
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.components.FBCDialog
import com.tana.facebookclone.presentation.components.LangPrefSection
import com.tana.facebookclone.presentation.registration.components.FBCPrimaryButton
import com.tana.facebookclone.presentation.registration.components.FBCSecondaryButton
import com.tana.facebookclone.presentation.registration.components.FBCTextButton
import com.tana.facebookclone.presentation.registration.components.FBCTextField

@Composable
fun SignInContent(
    focusManager: FocusManager,
    modifier: Modifier,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgetButtonClick: () -> Unit,
    onPhoneOrEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    uiState: SignInUiState,
    keyboardState: Keyboard,
    viewModel: SignInViewModel,
) {
    var preferredLang by remember { mutableStateOf("Kiswahili") }
    var icon = remember { mutableStateOf(R.drawable.hide_password_solid_icon) }
    var isDialogDismissed = remember { mutableStateOf(viewModel.isLoginFailed) }

    Surface() {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isDialogDismissed.value) {
                FBCDialog(
                    title = "Login Failed",
                    text = uiState.errorMessage!!,
                    onDismissRequest = {
                        isDialogDismissed.value = false
                    },
                    modifier = modifier
                )

            }
            AnimatedVisibility(visible = keyboardState.name == Keyboard.Closed.name) {
                Image(
                    painter = painterResource(id = R.drawable.facebook_login_header),
                    contentDescription = "Header",
                    modifier = modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
            AnimatedVisibility(visible = keyboardState.name == Keyboard.Opened.name) {
                Column() {
                    Spacer(modifier = modifier.height(64.dp))
                    Image(
                        painter = painterResource(id = R.drawable.facebook_logo),
                        contentDescription = "Facebook Logo",
                        modifier = modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                }
            }

            if (keyboardState.name == Keyboard.Closed.name) {
                Spacer(modifier = modifier.height(12.dp))
                LangPrefSection(
                    modifier = modifier,
                    preferredLang = preferredLang,
                    onLangSet = {
                        preferredLang = if (preferredLang == "Kiswahili") "English" else "Kiswahili"
                    }
                )
            }

            Spacer(modifier = modifier.height(32.dp))
            Column(
                modifier = modifier
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignInForm(
                    uiState = uiState,
                    onPhoneOrEmailChange = onPhoneOrEmailChange,
                    modifier = modifier,
                    keyboardState = keyboardState,
                    focusManager = focusManager,
                    onPasswordChange = onPasswordChange,
                    icon = icon,
                    onSignInClick = onSignInClick
                )
                Spacer(modifier = modifier.height(16.dp))
                FBCTextButton(
                    text = "Forgot Password?",
                    onClick = onForgetButtonClick,
                    modifier = modifier
                )
                if (keyboardState.name == Keyboard.Closed.name) {
                    Spacer(modifier = modifier.height(32.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Divider(
                            modifier = modifier.weight(1f)
                        )
                        Text(text = "OR")
                        Divider(
                            modifier = modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = modifier.height(32.dp))
                    FBCSecondaryButton(
                        text = "Create new Facebook account",
                        onClick = onSignUpClick,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.SignInForm(
    uiState: SignInUiState,
    onPhoneOrEmailChange: (String) -> Unit,
    modifier: Modifier,
    keyboardState: Keyboard,
    focusManager: FocusManager,
    onPasswordChange: (String) -> Unit,
    icon: MutableState<Int>,
    onSignInClick: () -> Unit
) {
    FBCTextField(
        value = uiState.phoneOrEmail,
        onValueChange = onPhoneOrEmailChange,
        label = "Phone or email",
        modifier = modifier
            .fillMaxWidth(),
        trailingIcon = if (keyboardState.name == Keyboard.Closed.name) {
            {
                Icon(
                    painter = painterResource(id = R.drawable.info_icon),
                    contentDescription = "",
                    modifier = modifier
                        .size(18.dp)
                        .clip(CircleShape)
                        .clickable { }
                )
            }
        } else null,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
    Spacer(modifier = modifier.height(4.dp))
    FBCTextField(
        value = uiState.password,
        label = "Password",
        modifier = modifier
            .fillMaxWidth(),
        onValueChange = onPasswordChange,
        trailingIcon = {
            AnimatedVisibility(visible = uiState.password.isNotBlank()) {
                Icon(
                    painter = painterResource(id = icon.value),
                    contentDescription = "Show or Hide password",
                    modifier = modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .clickable {
                            icon.value = if (icon.value == R.drawable.hide_password_solid_icon)
                                R.drawable.show_password_solid_icon else
                                R.drawable.hide_password_solid_icon
                        }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        visualTransformation = if (icon.value == R.drawable.hide_password_solid_icon)
            PasswordVisualTransformation() else
            VisualTransformation.None
    )
    Spacer(modifier = modifier.height(16.dp))
    FBCPrimaryButton(
        text = "Log In",
        onClick = onSignInClick,
        modifier = modifier
            .fillMaxWidth(),
        enabled = (uiState.phoneOrEmail.isNotBlank()) && (uiState.password.isNotBlank())
    )
}


enum class Keyboard {
    Opened,
    Closed
}

@Composable
fun keyboardAsState(): State<Keyboard> {

    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current

    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }
    return keyboardState

}