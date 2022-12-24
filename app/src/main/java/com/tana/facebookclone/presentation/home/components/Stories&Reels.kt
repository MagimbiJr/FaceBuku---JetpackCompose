package com.tana.facebookclone.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tana.facebookclone.R

@Composable
fun StoriesReels(
    imageDp: String,
    modifier: Modifier = Modifier
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Stories", "Reels")

    Surface() {
        Column {
            TabRow(
                selectedTabIndex = tabIndex,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                        color = MaterialTheme.colors.primary,
                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = tab) }
                    )
                }
            }
            Spacer(modifier = modifier.height(4.dp))
            if (tabIndex == 0) {
                Stories(
                    imageDp = imageDp
                )
            } else {
                Reels()
            }
        }
    }
}



