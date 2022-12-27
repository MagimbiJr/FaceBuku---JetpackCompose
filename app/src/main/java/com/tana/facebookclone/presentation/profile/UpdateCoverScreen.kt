package com.tana.facebookclone.presentation.profile

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.components.DisplayRationale
import com.tana.facebookclone.presentation.components.FBCTopAppBar
import com.tana.facebookclone.presentation.components.LoadingScreen
import com.tana.facebookclone.presentation.components.NoStoragePermission
import com.tana.facebookclone.presentation.profile.components.UpdateCoverContent
import com.tana.facebookclone.presentation.registration.components.FBCPrimaryButton
import com.tana.facebookclone.utils.AppEvents
import com.tana.facebookclone.utils.openSettings
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun UpdateCoverScreen(
    scaffoldState: ScaffoldState,
    onNavigateToHome: (AppEvents.Navigate) -> Unit,
    onPopBack: (AppEvents.PopBack) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val appState = viewModel.appEvents
    val galleryPermission = rememberPermissionState(
        permission = Manifest.permission.READ_EXTERNAL_STORAGE
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.updateUri(uri = uri)
    }
    val context = LocalContext.current

    LaunchedEffect(key1 = appState) {
        appState.collectLatest { event ->
            when (event) {
                is AppEvents.Navigate -> {
                    onNavigateToHome(event)
                }
                is AppEvents.PopBack -> {
                    onPopBack(event)
                }
                is AppEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is AppEvents.SignInRequired -> {}
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            FBCTopAppBar(
                title = "Update cover",
                navigationIcon = R.drawable.back_arrow_icon,
                onNavigationIconClick = viewModel::backIconClicked,
                modifier = modifier,
                actions = {
                    FBCPrimaryButton(
                        text = "Save",
                        onClick = viewModel::updateCoverPhoto,
                        modifier = modifier,
                        enabled = true
                    )
                },
                iconSize = 24.dp
            )
        }
    ) { paddingValues ->
        when (galleryPermission.status) {
            is PermissionStatus.Granted -> {
                LaunchedEffect(key1 = launcher) {
                    launcher.launch("image/*")
                }

                if (uiState.uri != null) {

                    UpdateCoverContent(
                        modifier = modifier
                            .padding(paddingValues),
                        onCreateAvatarBtnClicked = viewModel::createAvatarClicked,
                        uiState = uiState
                    )
                }
            }
            is PermissionStatus.Denied -> {
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
                        modifier = modifier
                    )
                }
            }
        }
    }
}