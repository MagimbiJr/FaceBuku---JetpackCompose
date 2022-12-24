package com.tana.facebookclone.data.sample_data

import com.tana.facebookclone.R

data class Post(
    val name: String,
    val caption: String,
    val image: Int,
    val postedAt: String,
    val likes: Int,
    val dp: Int,
    val comments: Int
)

val posts = listOf(
    Post(
        name = "Mtu Kazi",
        caption = "Pambana na maisha sio watu",
        image = R.drawable.mandonga_story,
        postedAt = "3d",
        likes = 32,
        dp = R.drawable.mandonga_dp,
        comments = 60
    ),
    Post(
        name = "Queen Mimah",
        caption = "Watu wanguvu kazini",
        image = R.drawable.story_3,
        postedAt = "31Min",
        likes = 16,
        dp = R.drawable.dp_2,
        comments = 3
    ),
    Post(
        name = "Mr Love",
        caption = "Maisha ni yetu sote",
        image = R.drawable.story_2,
        postedAt = "6d",
        likes = 87,
        dp = R.drawable.dp_1,
        comments = 55
    ),
    Post(
        name = "Madam Grace",
        caption = "Happy moment",
        image = R.drawable.story_4,
        postedAt = "Aug 16, 2022",
        likes = 21,
        dp = R.drawable.dp_4,
        comments = 37
    )
)