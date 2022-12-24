package com.tana.facebookclone.presentation.home.tab_screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.tana.facebookclone.data.sample_data.posts
import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.domain.modal.Post
import com.tana.facebookclone.domain.modal.User
import com.tana.facebookclone.presentation.home.components.AddPost
import com.tana.facebookclone.presentation.home.components.PostItem
import com.tana.facebookclone.presentation.home.components.StoriesReels
import com.tana.facebookclone.utils.StateManager

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FeedsScreen(
    user: User?,
    posts: List<Post>,
    comments: List<Comment>,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()

    StateManager.isFeedScrolled = remember {
        derivedStateOf { state.firstVisibleItemIndex > 0 }.value
    }

    LazyColumn(
        state = state,
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            AddPost(
                imageDp = user?.userProfilePic ?: "",
                onGalleryIconClick = {
                    navHostController.navigate("add_post")
                }
            )
        }
        item {
            StoriesReels(imageDp = user?.userProfilePic ?: "")
        }
        items(posts) { post ->
            PostItem(
                post = post,
                comments = comments,
                onCommentIconClick = {
                    navHostController.navigate("comments/${post.postId}")
                }
            )
        }
    }
}