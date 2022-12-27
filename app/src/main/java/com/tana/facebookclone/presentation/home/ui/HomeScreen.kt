package com.tana.facebookclone.presentation.home.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.firebase.auth.FirebaseAuth
import com.tana.facebookclone.presentation.components.LoadingScreen
import com.tana.facebookclone.presentation.ui_utils.TabScreens
import com.tana.facebookclone.utils.AppEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    goToSignIn: (AppEvents.SignInRequired) -> Unit,
    onNavigateToUpdateCover: (AppEvents.Navigate) -> Unit,
    navHostController: NavHostController,
    systemUiController: SystemUiController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    //uiState.user?.let { user ->
    val items = listOf(
        TabScreens.Home(
            navHostController = navHostController,
            posts = uiState.posts,
            user = uiState.user,
            comments = uiState.comments
        ),
        TabScreens.Friends, TabScreens.Watch,
        TabScreens.Profile(
            scope = scope,
            onNavigateToUpdateCover = onNavigateToUpdateCover,
            scaffoldState = scaffoldState
        ), TabScreens.Notification, TabScreens.Menu
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val appState by viewModel.uiState.collectAsState()
    systemUiController.setSystemBarsColor(MaterialTheme.colors.surface)

//    when(appState) {
//        is HomeUiState.Loading -> LoadingScreen()
//        is HomeUiState.Loaded -> HomeScreenContents(
//            items = items,
//            pagerState = pagerState,
//            coroutineScope = coroutineScope,
//            modifier = modifier
//        )
//    }

    LaunchedEffect(key1 = viewModel.appEvents) {
        viewModel.appEvents.collectLatest { event ->
            when (event) {
                is AppEvents.SignInRequired -> goToSignIn(event)
                is AppEvents.ShowSnackBar -> Unit
                is AppEvents.Navigate -> Unit
                is AppEvents.PopBack -> Unit
            }
        }
    }

    Scaffold {
        if (uiState.loading) {
            LoadingScreen()
        } else {
            if (FirebaseAuth.getInstance().currentUser != null) {
                HomeScreenContents(
                    items = items,
                    pagerState = pagerState,
                    coroutineScope = coroutineScope,
                    modifier = modifier.padding(it)
                )
            }
        }
    }
}