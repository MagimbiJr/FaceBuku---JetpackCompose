package com.tana.facebookclone.presentation.home.tab_screens

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.tana.facebookclone.presentation.components.DisplayRationale
import com.tana.facebookclone.presentation.components.LoadingScreen
import com.tana.facebookclone.presentation.components.NoStoragePermission
import com.tana.facebookclone.presentation.profile.ProfileContents
import com.tana.facebookclone.presentation.profile.ProfileViewModel
import com.tana.facebookclone.utils.AppEvents
import com.tana.facebookclone.utils.openSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun ProfileScreen(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onNavigateToUpdateCover: (AppEvents.Navigate) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val appEvent = viewModel.appEvents
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { modalBottomSheetValue ->
            modalBottomSheetValue != ModalBottomSheetValue.HalfExpanded
        }
    )

    LaunchedEffect(key1 = appEvent) {
        appEvent.collectLatest { event ->
            when(event) {
                is AppEvents.Navigate -> {
                    onNavigateToUpdateCover(event)
                }
                is AppEvents.PopBack -> {}
                is AppEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is AppEvents.SignInRequired -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->

        ProfileContents(
            uiState = uiState,
            bottomSheetState = bottomSheetState,
            onCreateAvatarClicked = viewModel::createAvatarClicked,
            onCreateCoverClicked = viewModel::createCoverClicked,
            scope = scope,
            modifier = modifier
                .padding(paddingValues)
        )
    }
}