package com.tana.facebookclone.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FBCTopAppBar(
    title: String,
    navigationIcon: Int,
    onNavigationIconClick: () -> Unit,
    modifier: Modifier,
    iconSize: Dp = 14.dp,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    textAlign: TextAlign = TextAlign.Start,
    elevation: Dp = 0.dp,
    dividerColor: Color = Color.Transparent
) {
    Divider(
        modifier = modifier
            .fillMaxWidth(),
        color = dividerColor,
        thickness = 1.5.dp
    )
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = modifier.fillMaxWidth(),
                textAlign = textAlign,
                fontSize = 20.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClick
            ) {
                Icon(
                    painter = painterResource(id = navigationIcon),
                    contentDescription = "Navigation icon",
                    modifier = modifier
                        .size(iconSize)
                )
            }
        },
        actions = actions,
        backgroundColor = backgroundColor,
        elevation = elevation
    )
}