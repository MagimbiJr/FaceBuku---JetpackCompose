package com.tana.facebookclone.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.facebookclone.R

@Composable
fun ProfileSheetContent(
    onCreateAvatarClicked: () -> Unit,
    onCreateCoverClicked: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onCreateAvatarClicked() }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.avatar_icon),
                contentDescription = "Avatar icon",
                modifier = modifier
                    .size(35.dp)
                    .padding(start = 12.dp)
            )
            Column(
                modifier = modifier
                    .padding(end = 12.dp)
            ) {
                Text(text = "Create avatar cover photo", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(
                        modifier = modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colors.primary, shape = CircleShape)
                    )
                    Text(text = "Upload new backgrounds", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSurface.copy(alpha = .6f))
                }
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onCreateAvatarClicked() }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.upload_icon),
                contentDescription = "upload icon",
                modifier = modifier
                    .size(35.dp)
                    .padding(start = 12.dp)
            )
            Text(text = "Update profile photo", fontWeight = FontWeight.Bold, fontSize = 17.sp)
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onCreateCoverClicked() }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.upload_icon),
                contentDescription = "upload icon",
                modifier = modifier
                    .size(35.dp)
                    .padding(start = 12.dp)
            )
            Text(text = "Update cover photo", fontWeight = FontWeight.Bold, fontSize = 17.sp)
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onCreateCoverClicked() }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.photo_collage_icon),
                contentDescription = "Photo collage icon",
                modifier = modifier
                    .size(35.dp)
                    .padding(start = 12.dp)
            )
            Text(text = "Create cover collage", fontWeight = FontWeight.Bold, fontSize = 17.sp)
        }
    }
}