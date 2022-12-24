package com.tana.facebookclone.presentation.registration.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.tana.facebookclone.presentation.theme.textFieldHeight

@Composable
fun FBCTextField(
    value: String,
    label: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    backgroundColor: Color = Color.Transparent,
    focusedIndicatorColor: Color = MaterialTheme.colors.primary,
    unFocusedIndicatorColor: Color = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.UnfocusedIndicatorLineOpacity),
) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        textColor = MaterialTheme.colors.onBackground.copy(.6f),
        focusedIndicatorColor = focusedIndicatorColor,
        unfocusedIndicatorColor = unFocusedIndicatorColor,
        backgroundColor = backgroundColor
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = textFieldColors,
        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.button,
                fontSize = 16.sp
            )
        },
        trailingIcon = trailingIcon,
        modifier = modifier
            .heightIn(textFieldHeight),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation
    )
}


@Preview(name = "Day Mode", showSystemUi = true)
@Composable
fun TextFieldPreview() {
    Surface() {
        //FBCTextField(value = "Text Field", onValueChange = {})
    }
}