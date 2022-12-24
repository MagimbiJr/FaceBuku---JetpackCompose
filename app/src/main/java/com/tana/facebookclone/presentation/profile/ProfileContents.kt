package com.tana.facebookclone.presentation.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tana.facebookclone.presentation.home.components.PostItem
import com.tana.facebookclone.presentation.profile.components.ProfileDetails
import com.tana.facebookclone.presentation.profile.components.ProfileHeader
import com.tana.facebookclone.presentation.profile.components.ProfileSheetContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileContents(
    uiState: ProfileUiState,
    bottomSheetState: ModalBottomSheetState,
    onCreateCoverClicked: () -> Unit,
    onCreateAvatarClicked: () -> Unit,
    scope: CoroutineScope,
    modifier: Modifier
) {
    ModalBottomSheetLayout(
        sheetContent = {
            ProfileSheetContent(
                onCreateAvatarClicked = onCreateAvatarClicked,
                onCreateCoverClicked = onCreateCoverClicked,
                modifier = modifier
            )
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        scrimColor = MaterialTheme.colors.background.copy(alpha = .7f)
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            item {
                ProfileHeader(
                    imageDp = uiState.user?.userProfilePic ?: "",
                    cover = uiState.user?.coverPhoto ?: "",
                    onClick = {
                        scope.launch {
                            bottomSheetState.show()
                        }
                    },
                    modifier = modifier
                )
            }
            item {
                ProfileDetails(
                    name = "${uiState.user?.firstName} ${uiState.user?.lastName}",
                    bio = uiState.user?.bio ?: "",
                    modifier = modifier
                )
            }
            items(items = uiState.posts) { post ->
                PostItem(post = post,comments = uiState.comments, onCommentIconClick = { /*TODO*/ })
            }
        }
    }
}