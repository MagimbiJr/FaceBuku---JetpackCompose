package com.tana.facebookclone.presentation.profile.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.components.FBCDialog
import com.tana.facebookclone.presentation.components.FBCTopAppBar
import com.tana.facebookclone.utils.AppEvents
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileSettingsScreen(
    scaffoldState: ScaffoldState,
    systemUiController: SystemUiController,
    onPopBack: (AppEvents.PopBack) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileSettingsViewModel = hiltViewModel()
) {
    systemUiController.setStatusBarColor(MaterialTheme.colors.primary)
    val appEvent = viewModel.appEvents

    var isDialogOpen = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = appEvent) {
        appEvent.collectLatest { event ->
            when (event) {
                is AppEvents.Navigate -> {}
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
                title = "Profile Settings",
                navigationIcon = R.drawable.back_arrow_icon,
                onNavigationIconClick = viewModel::onBackArrowClicked,
                modifier = modifier,
                iconSize = 24.dp,
                dividerColor = MaterialTheme.colors.onSurface
            )
        },
        modifier = modifier
            .fillMaxSize()
    ) { paddingValues ->
        val settingItem = remember { mutableStateOf("") }
        if (isDialogOpen.value) {
            FBCDialog(
                title = settingItem.value,
                text = "This setting is not implemented yet. Please visit some other time you might find something",
                onDismissRequest = { isDialogOpen.value = false },
                modifier = modifier
            )
        }
        ProfileSettingsContent(
            onClick = { item ->
                settingItem.value = item
                isDialogOpen.value = true
            },
            onCopyLinkClicked = viewModel::onCopyLinkClicked,
            modifier = modifier
                .padding(paddingValues)
        )
    }
}