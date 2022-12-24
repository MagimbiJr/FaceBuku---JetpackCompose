package com.tana.facebookclone.presentation.profile.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.theme.FacebookCloneTheme

@Composable
fun ProfileHeader(
    imageDp: String,
    cover: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .zIndex(1f)
    ) {
        CoverSection(
            cover = cover,
            onClick = onClick,
            modifier = modifier
        )
        Box(
            modifier = modifier
                .padding(start = 12.dp)
                .align(Alignment.BottomStart)
                .offset(y = 45.dp)

        ) {
            ProfileImage(
                imageDp = imageDp,
                onClick = onClick,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ProfileImage(
    imageDp: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    val dp = imageDp.ifBlank {
        R.drawable.person_icon
    }
    Box() {
        AsyncImage(
            model = ImageRequest.Builder(
                context = LocalContext.current
            )
                .data(dp)
                .placeholder(R.drawable.person_icon)
                .crossfade(enable = true)
                .build(),
            contentDescription = "",
            modifier = modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(
                    width = 1.5.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = CircleShape
                )
        )
        Card(
            modifier = modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-10).dp)
                .clip(CircleShape)
                .shadow(
                    elevation = 3.dp,
                    shape = CircleShape
                )
                .clickable { onClick() },
            shape = CircleShape,
            backgroundColor = if (isSystemInDarkTheme()) {
                MaterialTheme.colors.surface.copy(alpha = .1f)
            } else {
                MaterialTheme.colors.background
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.camera_icon),
                contentDescription = "Camera icon",
                modifier = modifier
                    .padding(8.dp)
                    .size(24.dp)
                    .align(Alignment.Center),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Composable
fun CoverSection(
    cover: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onClick() }
    ) {
        if (cover.isBlank()) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.camera_icon),
                    contentDescription = "Camera Icon",
                    modifier = modifier
                        .size(23.dp),
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(text = "Add Cover Photo", fontWeight = FontWeight.Bold)
            }
        } else {
            AsyncImage(
                model = ImageRequest.Builder(
                    context = LocalContext.current
                ).data(cover)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "Cover photo"
            )
        }
        Card(
            modifier = modifier
                .padding(bottom = 12.dp, end = 12.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (-10).dp)
                .clip(CircleShape)
                .clickable { onClick() },
            shape = CircleShape,
            backgroundColor = MaterialTheme.colors.surface.copy(alpha = .1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.camera_icon),
                contentDescription = "Camera icon",
                modifier = modifier
                    .padding(8.dp)
                    .size(24.dp)
                    .align(Alignment.Center),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileHeaderPreview() {
    FacebookCloneTheme() {
        Surface() {
            // ProfileImage(imageDp = "", modifier = Modifier)
        }
    }
}