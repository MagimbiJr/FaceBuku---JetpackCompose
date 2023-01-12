package com.tana.facebookclone.presentation.edit_profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.registration.components.FBCPrimaryButton

@Composable
fun AvatarSection(
    onCreateClicked: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = modifier,
            sectionName = "Avatar",
            onAddOrEditClick = onCreateClicked,
            clickableText = "Create"
        )
        Text(
            text = "Only you can view this section",
            fontSize = 12.sp,
            color = MaterialTheme.colors.onBackground.copy(.5f),
            modifier = modifier
                .fillMaxWidth()
        )
        Spacer(modifier = modifier.height(32.dp))
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.avatar_icon),
                contentDescription = "Avatar icon",
                modifier = modifier
                    .size(60.dp)
            )
            Spacer(modifier = modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.avatar_icon),
                contentDescription = "Avatar icon",
                modifier = modifier
                    .size(60.dp)
                    .offset(y = 30.dp)
            )
            Spacer(modifier = modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.avatar_icon),
                contentDescription = "Avatar icon",
                modifier = modifier
                    .size(60.dp)
            )
            Spacer(modifier = modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.avatar_icon),
                contentDescription = "Avatar icon",
                modifier = modifier
                    .size(60.dp)
                    .offset(y = 30.dp)
            )
            Spacer(modifier = modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.avatar_icon),
                contentDescription = "Avatar icon",
                modifier = modifier
                    .size(60.dp)
            )
        }
        Spacer(modifier = modifier.height(42.dp))
        FBCPrimaryButton(
            text = "Create avatar",
            onClick = onCreateClicked,
            modifier = modifier,
            enabled = true
        )
    }
}