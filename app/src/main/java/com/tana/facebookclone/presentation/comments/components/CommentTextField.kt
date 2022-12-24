package com.tana.facebookclone.presentation.comments.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CommentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
    backgroundColor: Color = MaterialTheme.colors.surface
) {
    val colors = TextFieldDefaults.textFieldColors(
        backgroundColor = backgroundColor,
        cursorColor = MaterialTheme.colors.onSurface,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(30.dp)
            .clip(CircleShape),
        placeholder = { Text(text = "Write a comment...") },
        colors = colors,
        keyboardOptions = keyboardOptions
    )
}
