package com.tana.facebookclone.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tana.facebookclone.R
import com.tana.facebookclone.data.sample_data.Reel
import com.tana.facebookclone.data.sample_data.reels
import com.tana.facebookclone.presentation.theme.DeepPink
import com.tana.facebookclone.presentation.theme.Gainsboro
import com.tana.facebookclone.presentation.theme.PrincetonOrange


@Composable
fun CreateReel(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(130.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(DeepPink, PrincetonOrange)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .offset(y = (-30).dp)
            ) {
                AddReelIcon(modifier = modifier)
            }
            Text(
                text = "Create reel",
                color = Gainsboro,
                modifier = modifier
                    .padding(bottom = 12.dp)
                    .align(Alignment.BottomCenter),
            )
        }
    }
}

@Composable
fun AddReelIcon(
    modifier: Modifier
) {
    Box(
    ) {
        Card(
            modifier = modifier
                .padding(top = 36.dp),
            shape = CircleShape,
            backgroundColor = Gainsboro
        ) {
            Icon(
                painter = painterResource(id = R.drawable.reels_icon),
                contentDescription = "",
                modifier = modifier
                    .padding(22.dp)
                    .size(34.dp),
                tint = DeepPink
            )
        }
        Box(
            modifier = modifier
                .offset(y = 93.dp, x = 50.dp)
        ) {
            Card(
                modifier = modifier,
                border = BorderStroke(width = 1.5.dp, brush = Brush.verticalGradient(listOf(DeepPink, PrincetonOrange))),
                shape = CircleShape,
                backgroundColor = Gainsboro
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = "",
                    modifier = modifier
                        .padding(8.dp)
                        .size(12.dp),
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Composable
fun ReelItem(
    reel: Reel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(130.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
    ) { 
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = reel.thumbnail),
                contentDescription = "",
                modifier = modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun Reels(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            CreateReel()
        }
        items(reels) { reel ->
            ReelItem(reel = reel)
        }
    }
}