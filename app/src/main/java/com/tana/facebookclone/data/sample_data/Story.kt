package com.tana.facebookclone.data.sample_data

import com.tana.facebookclone.R

data class Story(
    val userName: String,
    val userDp: Int,
    val storyImage: Int,
    val caption: String
)

val stories = listOf(
    Story(
        userName = "Jessy the don",
        userDp = R.drawable.dp_1,
        storyImage = R.drawable.story_1,
        caption = "Maisha ni yetu sote"
    ),
    Story(
        userName = "Queen Mimah",
        userDp = R.drawable.dp_2,
        storyImage = R.drawable.story_3,
        caption = "Friends for life"
    ),
    Story(
        userName = "Mr Love",
        userDp = R.drawable.dp_3,
        storyImage = R.drawable.story_2,
        caption = "Maisha ni yetu sote"
    ),
    Story(
        userName = "Madam Grace",
        userDp = R.drawable.dp_4,
        storyImage = R.drawable.story_4,
        caption = "Happy moment"
    ),
    Story(
        userName = "Mzito Chuma",
        userDp = R.drawable.dp_5,
        storyImage = R.drawable.story_5,
        caption = "Mzito na wazito wake"
    ),
    Story(
        userName = "Mtu kazi",
        userDp = R.drawable.mandonga_dp,
        storyImage = R.drawable.mandonga_story,
        caption = "Kazi kazi, si ubishoo"
    ),
    Story(
        userName = "Joe Biggie",
        userDp = R.drawable.dp_6,
        storyImage = R.drawable.story_6,
        caption = "Daddy an princess"
    )
)