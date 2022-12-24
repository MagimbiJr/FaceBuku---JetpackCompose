package com.tana.facebookclone.data.sample_data

import android.view.accessibility.CaptioningManager
import com.tana.facebookclone.R

data class Reel(
    val name: String,
    val caption: String,
    val video: Int,
    val thumbnail: Int,
    val likes: Int,
    val comments: List<String>
)

val reels = listOf(
    Reel(
        name = "Name",
        caption = "Captions",
        video = 1,
        thumbnail = R.drawable.story_6,
        likes = 2,
        comments = emptyList()
    ),
    Reel(
        name = "Name",
        caption = "Captions",
        video = 1,
        thumbnail = R.drawable.story_4,
        likes = 2,
        comments = emptyList()
    ),
    Reel(
        name = "Name",
        caption = "Captions",
        video = 1,
        thumbnail = R.drawable.story_7,
        likes = 2,
        comments = emptyList()
    ),
    Reel(
        name = "Name",
        caption = "Captions",
        video = 1,
        thumbnail = R.drawable.story_1,
        likes = 2,
        comments = emptyList()
    ),
    Reel(
        name = "Name",
        caption = "Captions",
        video = 1,
        thumbnail = R.drawable.story_2,
        likes = 2,
        comments = emptyList()
    ),
    Reel(
        name = "Name",
        caption = "Captions",
        video = 1,
        thumbnail = R.drawable.story_5,
        likes = 2,
        comments = emptyList()
    ),
    Reel(
        name = "Name",
        caption = "Captions",
        video = 1,
        thumbnail = R.drawable.mandonga_story,
        likes = 2,
        comments = emptyList()
    ),
    Reel(
        name = "Name",
        caption = "Captions",
        video = 1,
        thumbnail = R.drawable.story_6,
        likes = 2,
        comments = emptyList()
    )
)