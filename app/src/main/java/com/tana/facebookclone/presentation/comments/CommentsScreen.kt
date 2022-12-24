package com.tana.facebookclone.presentation.comments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.tana.facebookclone.presentation.registration.signin.ui.Keyboard
import com.tana.facebookclone.utils.AppEvents
import kotlinx.coroutines.flow.collectLatest


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommentsScreen(
    //postId: String,
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    keyboardState: Keyboard,
    viewModel: CommentViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val appEvents = viewModel.appEvents

    LaunchedEffect(key1 = appEvents) {
        appEvents.collectLatest { event ->
            when(event) {
                is AppEvents.Navigate -> Unit
                is AppEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is AppEvents.PopBack -> Unit
                is AppEvents.SignInRequired -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->
        CommentsContent(
            uiState = uiState,
            onAddCommentClick = { viewModel.addComment() },
            onCommentChange = viewModel::onCommentChange,
            keyboardState = keyboardState,
            modifier = modifier.padding(paddingValues)
        )
    }
}