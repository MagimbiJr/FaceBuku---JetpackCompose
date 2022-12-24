package com.tana.facebookclone.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tana.facebookclone.R
import com.tana.facebookclone.data.sample_data.Story
import com.tana.facebookclone.data.sample_data.stories

@Composable
fun UserStory(
    imageDp: String,
    modifier: Modifier = Modifier,
) {
    val dp = if (imageDp.isNotBlank()) {
        imageDp
    } else {
        R.drawable.person_icon
    }

    Card(
        modifier = modifier
            .width(130.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                //.padding(vertical = 12.dp)
                .padding(bottom = 24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }

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
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.FillWidth,
                colorFilter = if (imageDp.isNotBlank()) {
                    null
                } else {
                    ColorFilter.tint(MaterialTheme.colors.onSurface)
                }
            )
            Box(
                modifier = modifier
                    .align(Alignment.Center)
                    .offset(y = 28.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = "",
                    modifier = modifier
                        .clickable { }
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.5.dp,
                            color = MaterialTheme.colors.surface,
                            shape = CircleShape
                        )
                        .background(color = MaterialTheme.colors.primary)
                        .padding(12.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                )
            }
            Text(
                text = "Create Story", modifier = modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 12.dp)
            )
        }
    }
}

@Composable
fun StoryItem(
    story: Story,
    modifier: Modifier = Modifier,
    isViewed: Boolean
) {
    Card(
        modifier = modifier
            .width(130.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = story.userDp),
                contentDescription = "",
                modifier = modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = story.storyImage),
                contentDescription = "",
                modifier = modifier
                    .padding(top = 12.dp, start = 12.dp)
                    .size(35.dp)
                    .border(
                        width = 2.dp,
                        color = if (isViewed) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary,
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                contentScale = ContentScale.Fit
            )
            Text(
                text = story.userName,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                    .align(Alignment.BottomStart),
            )
        }
    }
}

@Composable
fun Stories(
    imageDp: String,
    modifier: Modifier = Modifier
) {


    LazyRow(
        modifier = modifier
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            UserStory(
                imageDp = imageDp
            )
        } 
        items(stories) { story: Story -> 
            StoryItem(story = story, isViewed = false)
        }
    }
}
