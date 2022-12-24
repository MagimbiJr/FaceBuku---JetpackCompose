package com.tana.facebookclone.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.tana.facebookclone.R

@Composable
fun FBCBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    backGroundColor: Color = MaterialTheme.colors.background
) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = backGroundColor,
        cursorColor = MaterialTheme.colors.primary,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeHolder, color = MaterialTheme.colors.onBackground.copy(.6f)) },
        colors = textFieldColors,
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
    )
}