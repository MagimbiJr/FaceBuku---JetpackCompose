package com.tana.facebookclone.presentation.home.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.tana.facebookclone.presentation.ui_utils.TabScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FBHomeTabs(
    items: List<TabScreens>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    modifier: Modifier
) {
    //var tabIndex by remember { mutableStateOf(0) }

    Column() {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.surface,
            indicator = {
                TabRowDefaults.Indicator(
                    modifier = modifier.tabIndicatorOffset(it[pagerState.currentPage]),
                    color = MaterialTheme.colors.primary
                )
            },
        ) {
            items.forEachIndexed { index, item ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = "Tab Icon",
                            modifier = modifier
                                .size(24.dp)
                        )
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContents(
    items: List<TabScreens>,
    pagerState: PagerState,
    modifier: Modifier
) {
    HorizontalPager(
        count = items.size,
        state = pagerState,
        modifier = modifier
            .fillMaxSize()
    ) { page ->
        items[page].screen()
    }
}


