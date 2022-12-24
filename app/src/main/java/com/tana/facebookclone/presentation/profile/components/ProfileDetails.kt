package com.tana.facebookclone.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileDetails(
    name: String,
    bio: String,
    modifier: Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 12.dp)
        ) {
            Spacer(modifier = modifier.height(70.dp))
            Text(text = name, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = bio, fontWeight = FontWeight.W500)
        }
    }
}