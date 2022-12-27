package com.tana.facebookclone.presentation.add_post

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.*
import com.tana.facebookclone.presentation.components.DisplayRationale
import com.tana.facebookclone.presentation.components.LoadingScreen
import com.tana.facebookclone.presentation.components.NoStoragePermission
import com.tana.facebookclone.utils.AppEvents
import com.tana.facebookclone.utils.openSettings
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddPostScreen(
    scrollState: ScrollState,
    navigate: (AppEvents.Navigate) -> Unit,
    popBack: (AppEvents.PopBack) -> Unit,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    viewModel: AddPostViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val appEvents = viewModel.appEvents
    val galleryPermission =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    //var imageData = remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.updateUri(uri)
    }

    LaunchedEffect(key1 = appEvents) {
        appEvents.collectLatest { event ->
            when(event) {
                is AppEvents.Navigate -> {
                    navigate(event)
                }
                is AppEvents.PopBack -> {
                    popBack(event)
                }
                is AppEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
                is AppEvents.SignInRequired -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        val paddingValues = it
        when (galleryPermission.status) {
            is PermissionStatus.Granted -> {
                LaunchedEffect(key1 = launcher) {
                    launcher.launch("image/*")
                }

                if (uiState.uri != null) {
                    if (uiState.loading) {
                        LoadingScreen()
                    } else {
                        Column() {

                            AddPostContent(
                                uiState = uiState,
                                onPostClick = { viewModel.addPost() },
                                onCaptionChange = viewModel::onCaptionChange,
                                onNavigationIconClick = viewModel::navigateBack,
                                scrollState = scrollState,
                                viewModel = viewModel,
                                modifier = modifier
                            )
                        }
                    }
                }
            }
            is PermissionStatus.Denied -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background),
                    contentAlignment = Alignment.Center
                ) {
                    if (galleryPermission.status.shouldShowRationale) {
                        DisplayRationale(
                            onGoToSetting = {
                                context.openSettings()
                            },
                            modifier = modifier
                        )
                    } else {
                        NoStoragePermission(
                            onGrantAccess = {
                                galleryPermission.launchPermissionRequest()
                            },
                            modifier = modifier,
                        )
                    }
                }
            }
        }
    }
}