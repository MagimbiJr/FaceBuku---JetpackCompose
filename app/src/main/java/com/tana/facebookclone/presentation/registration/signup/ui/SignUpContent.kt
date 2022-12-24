package com.tana.facebookclone.presentation.registration.signup.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.components.FBCDialog
import com.tana.facebookclone.presentation.components.LangPrefSection
import com.tana.facebookclone.presentation.registration.components.FBCPrimaryButton
import com.tana.facebookclone.presentation.registration.components.FBCSecondaryButton
import com.tana.facebookclone.presentation.registration.components.FBCTextField
import com.tana.facebookclone.presentation.registration.signin.ui.Keyboard
import com.tana.facebookclone.presentation.ui.DatePicker
import com.tana.facebookclone.utils.Gender
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignUpContent(
    uiState: SignUpUiState,
    focusManager: FocusManager,
    modifier: Modifier,
    scrollState: ScrollState,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onVerifyPassword: (String) -> Unit,
    onBirthDateChange: (LocalDate) -> Unit,
    onGenderChange: (String) -> Unit,
    keyboardState: Keyboard,
    viewModel: SignUpViewModel
) {
    var preferredLang by remember { mutableStateOf("Kiswahili") }
    var hidePasswordIcon = remember { mutableStateOf(R.drawable.hide_password_solid_icon) }
    var hideVerifyPasswordIcon = remember { mutableStateOf(R.drawable.hide_password_solid_icon) }
    var isDialogDismissed = remember { mutableStateOf(viewModel.isSignUpFailed) }


    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isDialogDismissed.value) {
            FBCDialog(
                title = "Registration Failed",
                text = uiState.errorMessage,
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
                .padding(horizontal = 32.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignUpForm(
                uiState = uiState,
                onFirstNameChange = onFirstNameChange,
                onLastNameChange = onLastNameChange,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onVerifyPassword = onVerifyPassword,
                onBirthDateChange = onBirthDateChange,
                onGenderChange = onGenderChange,
                modifier = modifier,
                keyboardState = keyboardState,
                focusManager = focusManager,
                hidePasswordIcon = hidePasswordIcon,
                hideVerifyPasswordIcon = hideVerifyPasswordIcon,
                onSignUpClick = onSignUpClick
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
                    text = "Sign In",
                    onClick = onSignInClick,
                    modifier = modifier
                        .widthIn(200.dp)
                )
            }
            Spacer(modifier = modifier.height(32.dp))
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun SignUpForm(
    uiState: SignUpUiState,
    modifier: Modifier,
    keyboardState: Keyboard,
    focusManager: FocusManager,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onVerifyPassword: (String) -> Unit,
    onBirthDateChange: (LocalDate) -> Unit,
    onGenderChange: (String) -> Unit,
    hidePasswordIcon: MutableState<Int>,
    hideVerifyPasswordIcon: MutableState<Int>,
    onSignUpClick: () -> Unit
) {
    var isVerifyTextFieldFocused = remember { mutableStateOf(false) }
    var isEmailFieldFocused = remember { mutableStateOf(false) }

    FBCTextField(
        value = uiState.firstName,
        onValueChange = onFirstNameChange,
        label = "First name",
        modifier = modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        backgroundColor = Color.Transparent
    )
    Spacer(modifier = modifier.height(4.dp))
    FBCTextField(
        value = uiState.lastName,
        onValueChange = onLastNameChange,
        label = "Last name",
        modifier = modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
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
        value = uiState.email,
        onValueChange = onEmailChange,
        label = "Email",
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    isEmailFieldFocused.value = focusState.isFocused
                }
            },
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
        ),
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
                    painter = painterResource(id = hidePasswordIcon.value),
                    contentDescription = "Show or Hide password",
                    modifier = modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .clickable {
                            hidePasswordIcon.value =
                                if (hidePasswordIcon.value == R.drawable.hide_password_solid_icon)
                                    R.drawable.show_password_solid_icon else
                                    R.drawable.hide_password_solid_icon
                        }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        visualTransformation = if (hidePasswordIcon.value == R.drawable.hide_password_solid_icon)
            PasswordVisualTransformation() else
            VisualTransformation.None
    )
    Spacer(modifier = modifier.height(4.dp))
    FBCTextField(
        value = uiState.verifyPassword,
        label = "Verify password",
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                if (focusState.isFocused)
                    isVerifyTextFieldFocused.value = focusState.isFocused
            },
        onValueChange = onVerifyPassword,
        trailingIcon = {
            AnimatedVisibility(visible = uiState.verifyPassword.isNotBlank()) {
                Icon(
                    painter = painterResource(id = hideVerifyPasswordIcon.value),
                    contentDescription = "Show or Hide password",
                    modifier = modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .clickable {
                            hideVerifyPasswordIcon.value =
                                if (hideVerifyPasswordIcon.value == R.drawable.hide_password_solid_icon)
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
        visualTransformation = if (hideVerifyPasswordIcon.value == R.drawable.hide_password_solid_icon)
            PasswordVisualTransformation() else
            VisualTransformation.None,
        focusedIndicatorColor = if ((isVerifyTextFieldFocused.value && uiState.verifyPassword.isNotBlank()) && (uiState.password != uiState.verifyPassword)) {
            Color.Red
        } else {
            MaterialTheme.colors.primary
        },
        unFocusedIndicatorColor = if ((isVerifyTextFieldFocused.value && uiState.verifyPassword.isNotBlank()) && (uiState.password != uiState.verifyPassword)) {
            Color.Red
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.UnfocusedIndicatorLineOpacity)
        }
    )
    Spacer(modifier = modifier.height(12.dp))
    DatePicker(value = uiState.birthDate, onDateChange = onBirthDateChange)
    Spacer(modifier = modifier.height(12.dp))
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        var selectedGender by remember { mutableStateOf("") }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedGender == Gender.Male.name,
                onClick = {
                    selectedGender = Gender.Male.name
                    onGenderChange(Gender.Male.name)
                }
            )
            Text(text = Gender.Male.name)
        }
        Spacer(modifier = modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedGender == Gender.Female.name,
                onClick = {
                    selectedGender = Gender.Female.name
                    onGenderChange(Gender.Female.name)
                }
            )
            Text(text = Gender.Female.name)
        }
    }
    Spacer(modifier = modifier.height(16.dp))
    FBCPrimaryButton(
        text = "Sign Up",
        onClick = onSignUpClick,
        modifier = modifier
            .fillMaxWidth(),
        enabled = (
                uiState.firstName.isNotBlank() && uiState.lastName.isNotBlank()
                        && uiState.email.isNotBlank()) && (uiState.password.isNotBlank()
                && uiState.verifyPassword.isNotBlank() && uiState.gender.isNotBlank()
                && uiState.password == uiState.verifyPassword
                )
    )
}
