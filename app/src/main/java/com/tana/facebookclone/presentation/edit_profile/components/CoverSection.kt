package com.tana.facebookclone.presentation.edit_profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.edit_profile.EditProfileUiState

@Composable
fun ProfileCoverSection(
    modifier: Modifier,
    onAddOrEditClick: () -> Unit,
    uiState: EditProfileUiState,
) {
    val clickableText = if (uiState.user?.coverPhoto == "")
        "Add"
    else
        "Edit"

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Header(
            sectionName = "Cover Photo",
            modifier = modifier,
            onAddOrEditClick = onAddOrEditClick,
            clickableText = clickableText
        )
        Spacer(modifier = modifier.height(12.dp))
        if (uiState.user?.coverPhoto == "") {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.surface)
                    .clickable { onAddOrEditClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gallery_icon),
                    contentDescription = "Gallery icon",
                    modifier = modifier
                        .size(40.dp)
                )
            }
        } else {
            AsyncImage(
                model = ImageRequest.Builder(
                    context = LocalContext.current
                )
                    .data(uiState.user?.coverPhoto)
                    .build(),
                contentDescription = "Cover photo",
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colors.background)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onAddOrEditClick() },
            )
        }
    }
}