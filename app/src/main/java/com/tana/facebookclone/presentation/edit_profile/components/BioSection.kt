package com.tana.facebookclone.presentation.edit_profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tana.facebookclone.presentation.edit_profile.EditProfileUiState

@Composable
fun BioSection(
    uiState: EditProfileUiState,
    onAddOrEditClick: () -> Unit,
    modifier: Modifier
) {
    val clickableText = if (uiState.user?.bio == "")
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
            sectionName = "Bio",
            onAddOrEditClick = onAddOrEditClick,
            clickableText = clickableText
        )
        if (uiState.user?.bio != "") {
            Text(text = uiState.user?.bio ?: "")
        }
    }
}