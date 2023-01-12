package com.tana.facebookclone.presentation.edit_profile

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.tana.facebookclone.R
import com.tana.facebookclone.presentation.components.FBCTopAppBar
import com.tana.facebookclone.utils.AppEvents
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditProfileScreen(
    systemUiController: SystemUiController,
    scrollState: ScrollState,
    onPopBack: (AppEvents.PopBack) -> Unit,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val appEvent = viewModel.appEvents
    systemUiController.setSystemBarsColor(MaterialTheme.colors.background)

    LaunchedEffect(key1 = appEvent) {
        appEvent.collectLatest { event ->
            when(event) {
                is AppEvents.Navigate -> Unit
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
        topBar = {
                 FBCTopAppBar(
                     title = "Edit Profile",
                     navigationIcon = R.drawable.back_arrow_icon,
                     onNavigationIconClick = viewModel::backButtonClicked,
                     modifier = modifier,
                     iconSize = 24.dp,
                     elevation = 4.dp
                 )
        },
        scaffoldState = scaffoldState,
    ) { paddingValues ->
        Divider()
        EditProfileContents(
            scrollState = scrollState,
            uiState = uiState,
            onAddOrEditProfileClick = viewModel::onAddOrEditProfileClick,
            onAddOrEditCoverClick = viewModel::onAddOrEditCoverClick,
            onCreateAvatarClick = viewModel::onCreateAvatarClick,
            onAddOrEditBioClick = viewModel::onAddOrEditBioClick,
            onAddOrEditDetailsClick = viewModel::onAddOrEditDetailsClick,
            onAddOrEditFeaturedClick = viewModel::onAddOrEditFeaturedClick,
            onAddOrEditHobbiesClick = viewModel::onAddOrEditHobbiesClick,
            onAddOrEditLinksClick = viewModel::onAddOrEditLinksClick,
            onEditInfoClick = viewModel::onEditInfoClick,
            modifier = modifier
                .padding(paddingValues)
        )
    }
}