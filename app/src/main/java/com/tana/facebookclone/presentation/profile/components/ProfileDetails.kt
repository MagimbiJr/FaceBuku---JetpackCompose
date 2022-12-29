package com.tana.facebookclone.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tana.facebookclone.presentation.registration.components.FBCSecondaryButton
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.theme.buttonHeight

@Composable
fun ProfileDetails(
    name: String,
    bio: String,
    onAddToStoryClicked: () -> Unit,
    onEditProfileClicked: () -> Unit,
    onMoreClicked: () -> Unit,
    modifier: Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 12.dp)
        ) {
            Spacer(modifier = modifier.height(70.dp))
            Text(text = name, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = bio, fontWeight = FontWeight.W500)
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileButton(
                    text = "Add to story",
                    icon = R.drawable.add_round_icon,
                    onClick = onAddToStoryClicked,
                    modifier = modifier,
                    backgroundColor = MaterialTheme.colors.primary
                )
                ProfileButton(
                    text = "Edit profile",
                    icon = R.drawable.edit_icon,
                    onClick = onEditProfileClicked,
                    modifier = modifier,
                    backgroundColor = MaterialTheme.colors.onSurface.copy(.3f)
                )
                ProfileButton(
                    icon = R.drawable.more_horizontal_icon,
                    onClick = onMoreClicked,
                    modifier = modifier
                        .width(80.dp),
                    backgroundColor = MaterialTheme.colors.onSurface.copy(.3f)
                )
            }
            Spacer(modifier = modifier.height(16.dp))
        }
    }
}

@Composable
private fun ProfileButton(
    text: String? = null,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier,
    backgroundColor: Color
) {
    Card(
        modifier = modifier
            .heightIn(buttonHeight)
            .clickable { onClick() },
        backgroundColor = backgroundColor,
        shape = MaterialTheme.shapes.medium

    ) {
        Row(
            modifier = modifier
                .background(Color.Transparent)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "more icon",
                modifier = modifier
                    .size(14.dp)
            )
            if (text != null) {
                Spacer(modifier = modifier.width(12.dp))
                Text(
                    text = text,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}