package com.tana.facebookclone.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun FBCDialog(
    title: String,
    text: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Text(
                text = "OK",
                color = MaterialTheme.colors.primary,
                modifier = modifier
                    .padding(bottom = 24.dp, end = 24.dp)
                    .clickable { onDismissRequest() }
            )
                        },
        title = { Text(text = title) },
        text = { Text(text = text) }
    )
}