package com.tana.facebookclone.presentation.edit_profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.edit_profile.EditProfileUiState


@Composable
fun ProfilePictureSection(
    uiState: EditProfileUiState,
    onAddOrEditClick: () -> Unit,
    modifier: Modifier
) {
    val clickableText = if (uiState.user?.userProfilePic == "")
        "Add"
    else
        "Edit"
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = modifier,
            sectionName = "Profile Picture",
            onAddOrEditClick = onAddOrEditClick,
            clickableText = clickableText
        )
        Spacer(modifier = modifier.height(16.dp))
        AsyncImage(
            model = ImageRequest.Builder(
                context = LocalContext.current
            )
                .data(uiState.user?.userProfilePic)
                .placeholder(R.drawable.person_icon)
                .build(),
            contentDescription = "Profile picture",
            modifier = modifier
                .size(130.dp)
                .clip(CircleShape)
                .clickable { onAddOrEditClick() },
            contentScale = ContentScale.Crop
        )
    }
}