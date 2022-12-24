package com.tana.facebookclone.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LangPrefSection(
    modifier: Modifier,
    preferredLang: String,
    onLangSet: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = preferredLang,
            modifier = modifier
                .clickable { onLangSet() },
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(text = '\u2022'.toString(), fontSize = 24.sp)
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = "More...",
            color = MaterialTheme.colors.primary,
            modifier = modifier
                .clickable { }
        )
    }
}