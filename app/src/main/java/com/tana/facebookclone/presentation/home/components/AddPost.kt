package com.tana.facebookclone.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tana.facebookclone.R


@Composable
fun AddPost(
    imageDp: String,
    onGalleryIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dp = if (imageDp.isNotBlank()) {
        imageDp
    } else {
        R.drawable.person_icon
    }
    Surface() {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(
                    context = LocalContext.current
                )
                    .data(data = dp)
                    .placeholder(R.drawable.person_icon)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "",
                modifier = modifier
                    .clip(CircleShape)
                    .size(40.dp),
                contentScale = ContentScale.Crop,
                colorFilter = if (imageDp.isNotBlank()) {
                    null
                } else {
                    ColorFilter.tint(MaterialTheme.colors.onSurface)
                }
            )
            Spacer(modifier = modifier.width(8.dp))
            Box(
                modifier = modifier
                    .weight(1f)
                    .border(
                        width = .5.dp,
                        color = MaterialTheme.colors.onSurface,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable { },
            ) {
                Text(
                    text = stringResource(id = R.string.whats_on_your_mind),
                    modifier = modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                )
            }
            Spacer(modifier = modifier.width(8.dp))
            IconButton(onClick = onGalleryIconClick) {
                Image(
                    painter = painterResource(id = R.drawable.gallery_colored_icon),
                    contentDescription = "",
                    modifier = modifier.size(24.dp),
                )
            }
        }
    }
}
