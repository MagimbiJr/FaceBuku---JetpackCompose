package com.tana.facebookclone.presentation.comments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tana.facebookclone.presentation.comments.components.CommentTextField
import com.tana.facebookclone.R
import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.presentation.comments.components.Comments
import com.tana.facebookclone.presentation.registration.signin.ui.Keyboard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommentsContent(
    uiState: CommentsUiState,
    onAddCommentClick: () -> Unit,
    onCommentChange: (String) -> Unit,
    keyboardState: Keyboard,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .weight(1f)
        ) {
            if (uiState.comments == emptyList<Comment>()) {
                NoCommentContent(modifier = modifier)
            }
            Column(
                modifier = modifier
                    .padding(12.dp)
            ) {
                uiState.user?.let { user ->
                    Comments(user = user, comments = uiState.comments, modifier = modifier)
                }
            }
        }
        Spacer(modifier = modifier.height(4.dp))
        Divider()
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 16.dp)
                .padding(
                    top = 8.dp,
                    bottom = if (keyboardState.name == Keyboard.Opened.name)
                        12.dp else 0.dp
                ),
        ) {
            CommentTextField(
                value = uiState.comment,
                onValueChange = onCommentChange,
                modifier = modifier
            )
            if (keyboardState.name == Keyboard.Opened.name) {
                Spacer(modifier = modifier.height(12.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.camera_icon),
                            contentDescription = "Camera icon",
                            modifier = modifier
                                .size(24.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.gif_icon),
                            contentDescription = "Camera icon",
                            modifier = modifier
                                .size(24.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.emoji_icon),
                            contentDescription = "Camera icon",
                            modifier = modifier
                                .size(24.dp)
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.send_outline),
                        contentDescription = "Send button",
                        modifier = modifier
                            .size(24.dp)
                            .clickable { onAddCommentClick() }
                    )
                }
            }
        }
    }
}

@Composable
fun NoCommentContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(120.dp))
        Icon(
            painter = painterResource(id = R.drawable.messages_icon),
            contentDescription = "",
            modifier = modifier
                .size(90.dp)
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(text = "No comments yet", fontWeight = FontWeight.Bold)

        Text(text = "Be the first to comment")
    }
}