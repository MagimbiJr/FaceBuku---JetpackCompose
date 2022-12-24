package com.tana.facebookclone.presentation.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.google.firebase.Timestamp
import com.tana.facebookclone.R
import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.domain.modal.Post
import com.tana.facebookclone.presentation.theme.Gainsboro
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.Hours
import org.joda.time.Minutes
import org.joda.time.MutableDateTime
import org.joda.time.Weeks
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostItem(
    post: Post,
    comments: List<Comment>,
    onCommentIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        Column(
            modifier = modifier
                .padding(vertical = 12.dp)
        ) {
            post.user?.let { user ->
                user.userProfilePic?.let { userAvatar ->
                    post.postedAt?.let { postedAt ->
                        PostTitle(
                            userName = "${user.firstName} ${user.lastName}",
                            userAvatar = userAvatar,
                            postedAt = postedAt,
                            modifier = modifier
                        )
                    }
                }
            }
            post.postCaption?.let { caption ->
                if (caption.isNotBlank()) {
                    Spacer(modifier = modifier.height(8.dp))
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(text = caption, maxLines = 3, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
            Spacer(modifier = modifier.height(8.dp))
            AsyncImage(
                model = ImageRequest.Builder(
                    context = LocalContext.current
                )
                    .data(post.postImage)
                    .build(),
                contentDescription = "",
                modifier = modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = modifier.height(8.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Card(
                            shape = CircleShape,
                            backgroundColor = MaterialTheme.colors.primary
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.facebook_like_icon),
                                contentDescription = "Like image",
                                modifier = modifier
                                    .size(18.dp)
                                    .padding(4.dp),
                                tint = Gainsboro
                            )
                        }
                        Text(text = "12", fontSize = 12.sp)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (comments.isNotEmpty()) {
                            Text(text = "${comments.size} comments", fontSize = 12.sp)
                            Text(text = '\u2022'.toString(), fontSize = 24.sp)
                        }
                        Text(text = "${12} share", fontSize = 12.sp)
                    }
                }
                Divider()
                Spacer(modifier = modifier.height(4.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = modifier
                            .weight(1f)
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = modifier
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.facebook_like_outline_icon),
                                contentDescription = "",
                                modifier = modifier
                                    .size(18.dp),
                                tint = MaterialTheme.colors.onSurface
                            )
                            //Spacer(modifier = modifier.width(4.dp))
                            Text(text = "Like", fontSize = 14.sp)
                        }
                    }
                    //Spacer(modifier = modifier.width(4.dp))
                    Row(
                        modifier = modifier
                            .weight(1f)
                            .clickable { onCommentIconClick() },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = modifier
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.text_msg_triange_outline_icon),
                                contentDescription = "",
                                modifier = modifier
                                    .size(18.dp),
                                tint = MaterialTheme.colors.onSurface
                            )
                            //Spacer(modifier = modifier.width(4.dp))
                            Text(text = "Comment", fontSize = 14.sp)
                        }
                    }
                    //Spacer(modifier = modifier.width(4.dp))
                    Row(
                        modifier = modifier
                            .weight(1f)
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = modifier
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.messanger_outline_logo),
                                contentDescription = "",
                                modifier = modifier
                                    .size(18.dp),
                                tint = MaterialTheme.colors.onSurface
                            )
                            //Spacer(modifier = modifier.width(4.dp))
                            Text(text = "Send", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostTitle(
    userName: String,
    userAvatar: String,
    postedAt: Timestamp,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(
                context = LocalContext.current
            )
                .data(userAvatar)
                .placeholder(R.drawable.person_icon)
                .build(),
            contentDescription = "",
            modifier = modifier
                .size(35.dp)
                .clip(CircleShape)
                .clickable { },
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.width(4.dp))
        Column(
            modifier = modifier
                .fillMaxWidth(.75f)
        ) {
            Text(text = userName)
            Row(
                horizontalArrangement = Arrangement.spacedBy(3.dp),
            ) {
                val displayedDay = remember { mutableStateOf("") }
                val date = LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(postedAt.seconds),
                    ZoneId.of(TimeZone.getDefault().id)
                )
                val selectedTime =
                    DateTime(date.year, date.month.value, date.dayOfMonth, date.hour, date.minute)
                val selectedTimeInMillis = selectedTime.millis
                val epoch = MutableDateTime()
                epoch.setDate(selectedTimeInMillis)
                val now = DateTime()
                val currentTimeInMillis = now.millis
                val minutes = Minutes.minutesBetween(selectedTime, now).minutes
                val hours = Hours.hoursBetween(selectedTime, now).hours
                val days = Days.daysBetween(selectedTime, now).days
                val weeks = Weeks.weeksBetween(epoch, selectedTime).weeks

                displayedDay.value = if (minutes <= 1) {
                    "Now"
                } else if (minutes in 2..59) {
                    "${minutes}min"
                } else if (hours in 1..23) {
                    "${hours}h"
                } else if (days in 1..6) {
                    "${days}d"
                } else {
                    "${date.month.toString().take(3)} ${date.dayOfMonth}"
                }


                Text(
                    text = displayedDay.value,
                    color = MaterialTheme.colors.onSurface.copy(.4f),
                    fontSize = 12.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.people_icon),
                    contentDescription = "",
                    modifier = modifier
                        .size(12.dp),
                    tint = MaterialTheme.colors.surface.copy(.4f)
                )
            }
        }
        Spacer(modifier = modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = modifier
                    .clip(CircleShape)
                    .clickable { },
                shape = CircleShape,
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.more_horizontal_icon),
                    contentDescription = "",
                    modifier = modifier
                        .padding(8.dp)
                        .size(14.dp)
                )
            }
            Card(
                modifier = modifier
                    .clip(CircleShape)
                    .clickable { },
                shape = CircleShape,
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "",
                    modifier = modifier
                        .padding(8.dp)
                        .size(14.dp)
                )
            }
        }
    }
}
