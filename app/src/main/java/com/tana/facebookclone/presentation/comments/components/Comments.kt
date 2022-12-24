package com.tana.facebookclone.presentation.comments.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.domain.modal.User

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Comments(
    user: User,
    comments: List<Comment>,
    modifier: Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(comments) { comment ->
            CommentItem(
                user = user,
                comment = comment,
                modifier = modifier
            )
        }
    }
}