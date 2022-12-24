package com.tana.facebookclone.presentation.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.tana.facebookclone.presentation.home.components.FBHomeTabs
import com.tana.facebookclone.presentation.home.components.FBHomeTopBar
import com.tana.facebookclone.presentation.home.components.TabContents
import com.tana.facebookclone.presentation.ui_utils.TabScreens
import com.tana.facebookclone.utils.StateManager
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenContents(
    items: List<TabScreens>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            AnimatedVisibility(
                visible = pagerState.currentPage == 0 || StateManager.isFeedScrolled,
                enter = slideInVertically()
            ) {
                FBHomeTopBar(modifier = modifier)
            }
            FBHomeTabs(
                items = items,
                pagerState = pagerState,
                coroutineScope = coroutineScope,
                modifier = modifier
            )
            TabContents(items = items, pagerState = pagerState, modifier = modifier)
        }
    }
}