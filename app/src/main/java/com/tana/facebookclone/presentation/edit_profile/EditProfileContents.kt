package com.tana.facebookclone.presentation.edit_profile

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tana.facebookclone.presentation.edit_profile.components.*
import com.tana.facebookclone.presentation.registration.components.FBCPrimaryButton

@Composable
fun EditProfileContents(
    uiState: EditProfileUiState,
    scrollState: ScrollState,
    onAddOrEditProfileClick: () -> Unit,
    onAddOrEditCoverClick: () -> Unit,
    onCreateAvatarClick: () -> Unit,
    onAddOrEditBioClick: () -> Unit,
    onAddOrEditDetailsClick: () -> Unit,
    onAddOrEditHobbiesClick: () -> Unit,
    onAddOrEditFeaturedClick: () -> Unit,
    onAddOrEditLinksClick: () -> Unit,
    onEditInfoClick: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(state = scrollState)
    ) {
        ProfilePictureSection(
            uiState = uiState,
            onAddOrEditClick = onAddOrEditProfileClick,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))
        Divider()
        Spacer(modifier = modifier.height(12.dp))
        ProfileCoverSection(
            modifier = modifier,
            onAddOrEditClick = onAddOrEditCoverClick,
            uiState = uiState
        )
        Spacer(modifier = modifier.height(12.dp))
        Divider()
        Spacer(modifier = modifier.height(12.dp))
        AvatarSection(
            onCreateClicked = onCreateAvatarClick,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))
        Divider()
        Spacer(modifier = modifier.height(12.dp))
        BioSection(
            uiState = uiState,
            onAddOrEditClick = onAddOrEditBioClick,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))
        Divider()
        Spacer(modifier = modifier.height(12.dp))
        DetailsSection(
            onAddOrEditClick = onAddOrEditDetailsClick,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))
        Divider()
        Spacer(modifier = modifier.height(12.dp))
        HobbiesSection(
            onAddOrEditClick = onAddOrEditHobbiesClick,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))
        Divider()
        Spacer(modifier = modifier.height(12.dp))
        FeaturesSection(
            onTryItClick = onAddOrEditFeaturedClick,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))
        Divider()
        Spacer(modifier = modifier.height(12.dp))
        LinksSection(
            onAddOrEditClick = onAddOrEditLinksClick,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))
        Divider()
        Spacer(modifier = modifier.height(12.dp))
        FBCPrimaryButton(
            text = "Edit Your About Info",
            onClick = onEditInfoClick,
            modifier = modifier
                .fillMaxWidth(),
            enabled = true,
            backgroundColor = MaterialTheme.colors.primary.copy(.3f)
        )
    }
}










