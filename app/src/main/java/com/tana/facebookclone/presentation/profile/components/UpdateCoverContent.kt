package com.tana.facebookclone.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tana.facebookclone.presentation.profile.ProfileViewModel
import com.tana.facebookclone.presentation.registration.components.FBCSecondaryButton
import com.tana.facebookclone.R

@Composable
fun UpdateCoverContent(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box {
            AsyncImage(
                model = uiState.uri?.toString(),
                contentDescription = "Selected Cover Photo",
                modifier = modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Box(
                modifier = modifier
                    .padding(start = 12.dp)
                    .align(Alignment.BottomStart)
                    .offset(y = 45.dp)

            ) {
                ProfileImage(
                    imageDp = uiState.user?.userProfilePic ?: "",
                    onClick = { /*TODO*/ },
                    modifier = modifier
                )
            }
        }
        Spacer(modifier = modifier.weight(1f))
        Column(
            modifier = modifier
                .padding(horizontal = 12.dp)
        ) {
            FBCSecondaryButton(
                text = "Create avatar cover",
                icon = R.drawable.avatar_icon,
                onClick = { /*TODO*/ },
                modifier = modifier
                    .fillMaxWidth()
            )
        }
    }
}