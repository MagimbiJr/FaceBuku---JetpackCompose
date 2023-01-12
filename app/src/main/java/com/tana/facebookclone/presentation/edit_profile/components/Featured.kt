package com.tana.facebookclone.presentation.edit_profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.registration.components.FBCPrimaryButton

@Composable
fun FeaturesSection(
    onTryItClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = modifier,
            sectionName = "Features",
            onAddOrEditClick = onTryItClick,
            clickableText = ""
        )
        Spacer(modifier = modifier.height(32.dp))
        Icon(
            painter = painterResource(id = R.drawable.rubik_qube_starked_icon),
            contentDescription = "Features icon",
            modifier = modifier
                .size(90.dp)
        )
        Spacer(modifier = modifier.height(12.dp))
        Text(
            text = "Feature your favorite photos and stories here for all yours friends to see",
            color = MaterialTheme.colors.onBackground.copy(.5f),
            modifier = modifier
                .fillMaxWidth(),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(8.dp))
        FBCPrimaryButton(
            text = "Try It",
            onClick = onTryItClick,
            modifier = modifier
                .fillMaxWidth(),
            enabled = true,
            backgroundColor = MaterialTheme.colors.surface
        )
    }
}