package com.tana.facebookclone.presentation.profile

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.shouldShowRationale
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.components.DisplayRationale
import com.tana.facebookclone.presentation.components.FBCTopAppBar
import com.tana.facebookclone.presentation.components.LoadingScreen
import com.tana.facebookclone.presentation.components.NoStoragePermission
import com.tana.facebookclone.presentation.profile.components.UpdateProfileContent
import com.tana.facebookclone.presentation.registration.components.FBCPrimaryButton
import com.tana.facebookclone.utils.AppEvents
import com.tana.facebookclone.utils.openSettings
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun UpdateProfileScreen(
    galleryPermission: PermissionState,
    scaffoldState: ScaffoldState,
    onNavigateToHome: (AppEvents.Navigate) -> Unit,
    onPopBack: (AppEvents.PopBack) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val appEvent = viewModel.appEvents
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.updateUri(uri = uri)
    }
    val context = LocalContext.current

    LaunchedEffect(key1 = appEvent) {
        appEvent.collectLatest { event ->
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
                is AppEvents.SignInRequired -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            FBCTopAppBar(
                title = "Update profile",
                navigationIcon = R.drawable.back_arrow_icon,
                onNavigationIconClick = viewModel::backIconClicked,
                modifier = modifier,
                actions = {
                    FBCPrimaryButton(
                        text = "Save",
                        onClick = viewModel::updateProfilePhoto,
                        modifier = modifier,
                        enabled = true,
                    )
                },
                iconSize = dimensionResource(id = R.dimen.back_icon_size)
            )
        }
    ) { paddingValues ->
        when (galleryPermission.status) {
            is PermissionStatus.Granted -> {
                LaunchedEffect(key1 = launcher) {
                    launcher.launch("image/*")
                }
                if (uiState.uri != null) {
                    if (uiState.loading) {
                        LoadingScreen()
                    } else {
                        UpdateProfileContent(
                            uiState = uiState,
                            onCreateAvatarBtnClicked = viewModel::createAvatarClicked,
                            modifier = modifier
                                .padding(paddingValues)
                        )
                    }
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