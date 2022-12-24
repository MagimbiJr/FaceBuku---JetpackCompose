package com.tana.facebookclone.presentation.comments.components

import android.icu.text.RelativeDateTimeFormatter
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tana.facebookclone.R
import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.domain.modal.User
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.Hours
import org.joda.time.Minutes
import org.joda.time.Months
import org.joda.time.MutableDateTime
import org.joda.time.Weeks
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.TimeZone
import kotlin.time.Duration.Companion.hours

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommentItem(
    user: User,
    comment: Comment,
    modifier: Modifier
) {
    Column() {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(
                    context = LocalContext.current
                )
                    .data(user.userProfilePic)
                    .placeholder(R.drawable.person_icon)
                    .crossfade(true)
                    .build(),
                contentDescription = "User profile",
                modifier = modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )
            Card(
                shape = RoundedCornerShape(35)
            ) {
                Column(
                    modifier = modifier
                        .padding(horizontal = 24.dp, vertical = 6.dp)
                ) {
                    Text(text = "${user.firstName} ${user.lastName}", fontWeight = FontWeight.Bold)
                    comment.comment?.let { comment ->
                        Text(text = comment, fontSize = 14.sp)
                    }
                }
            }
        }
        Spacer(modifier = modifier.height(4.dp))
        Row(
            modifier = modifier
                .padding(start = 46.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            comment.commentedAt?.let { timeStamp ->
                Log.d("TAG", "CommentItem: time stamp object: $timeStamp")
                val displayedDay = remember { mutableStateOf("") }
                val date = LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(timeStamp.seconds),
                    ZoneId.of(TimeZone.getDefault().id)
                )
                val selectedTime = DateTime(date.year,date.month.value,date.dayOfMonth,date.hour, date.minute)
                val selectedTimeInMillis = selectedTime.millis
                val epoch = MutableDateTime()
                epoch.setDate(selectedTimeInMillis)
                val now = DateTime()
                val currentTimeInMillis = now.millis
                val minutes = Minutes.minutesBetween(selectedTime, now).minutes
                val hours = Hours.hoursBetween(selectedTime, now).hours
                val days = Days.daysBetween(selectedTime, now).days
                val weeks = Weeks.weeksBetween(selectedTime, now).weeks

                displayedDay.value = if (minutes <= 1) {
                    "Now"
                } else if (minutes in 2..59) {
                    "${minutes}min"
                } else if (hours in 1..23) {
                    "${hours}h"
                } else if (days in 1..6) {
                    "${days}d"
                }
                else {
                    "${weeks}w"
                }
                Log.d("TAG", "CommentItem: time passed is ${displayedDay.value}")
                Log.d("TAG", "CommentItem: minutes passed is $minutes")
                Text(text = displayedDay.value, fontSize = 13.sp)
            }
            Text(
                text = "Like",
                fontSize = 13.sp,
                modifier = modifier
                    .clickable { },
            )
            Text(
                text = "Reply",
                fontSize = 13.sp,
                modifier = modifier
                    .clickable { },
            )
        }
    }
}