package com.tana.facebookclone.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tana.facebookclone.R

@Composable
fun FBHomeTopBar(
    modifier: Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "facebuku",
                color = if (isSystemInDarkTheme()) MaterialTheme.colors.onSurface else
                    MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.padding(8.dp).weight(1f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.background)
                        .clickable {  },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus_icon),
                        contentDescription = "Add",
                        modifier = modifier
                            .padding(8.dp)
                            .size(16.dp),
                        tint = MaterialTheme.colors.onSurface
                    )
                }
                Box(
                    modifier = modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.background)
                        .clickable {  },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "Search",
                        modifier = modifier
                            .padding(8.dp)
                            .size(16.dp),
                        tint = MaterialTheme.colors.onSurface
                    )
                }
                Box(
                    modifier = modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.background)
                        .clickable {  },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.messenger_icon),
                        contentDescription = "Message",
                        modifier = modifier
                            .padding(8.dp)
                            .size(20 .dp),
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun TopBarPreview() {
//    Surface() {
//        FBHomeTopBar(modifier = Modifier)
//    }
//}