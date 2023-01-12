package com.tana.facebookclone.presentation.edit_profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DetailsSection(
    onAddOrEditClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = modifier,
            sectionName = "Details",
            onAddOrEditClick = onAddOrEditClick,
            clickableText = "Add"
        )
    }
}