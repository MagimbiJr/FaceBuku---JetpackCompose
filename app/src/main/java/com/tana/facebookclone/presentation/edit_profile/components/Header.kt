package com.tana.facebookclone.presentation.edit_profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    modifier: Modifier,
    sectionName: String,
    onAddOrEditClick: () -> Unit,
    clickableText: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = sectionName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = clickableText,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            fontSize = 18.sp,
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clickable { onAddOrEditClick() }
        )
    }
}