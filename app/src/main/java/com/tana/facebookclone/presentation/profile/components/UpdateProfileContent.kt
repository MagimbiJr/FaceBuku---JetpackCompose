package com.tana.facebookclone.presentation.profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.profile.ProfileUiState
import com.tana.facebookclone.presentation.registration.components.FBCSecondaryButton

@Composable
fun UpdateProfileContent(
    uiState: ProfileUiState,
    onCreateAvatarBtnClicked: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box {
            CoverSection(
                cover = uiState.user?.coverPhoto ?: "",
                onClick = { /*TODO*/ },
                modifier = modifier
            )
            Box(
                modifier = modifier
                    .padding(start = 12.dp)
                    .align(Alignment.BottomStart)
                    .offset(y = 45.dp)

            ) {
                AsyncImage(
                    model = uiState.uri?.toString(),
                    contentDescription = "Selected profile photo",
                    modifier = modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.5.dp,
                            color = MaterialTheme.colors.onSurface,
                            shape = CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Column(
            modifier = modifier
                .padding(horizontal = 12.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = modifier.weight(1f))
            FBCSecondaryButton(
                text = "Create avatar cover",
                icon = R.drawable.avatar_icon,
                onClick = onCreateAvatarBtnClicked,
                modifier = modifier,
                backgroundColor = MaterialTheme.colors.surface
            )
            Spacer(modifier = modifier.height(12.dp))
        }
    }
}