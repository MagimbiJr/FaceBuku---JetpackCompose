package com.tana.facebookclone.domain.modal

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.tana.facebookclone.utils.Resource
import java.util.UUID

data class Post(
    val postId: String = UUID.randomUUID().toString(),
    val postCaption: String? = "",
    val postImage: String? = "",
    val postedAt: Timestamp? = Timestamp.now(),
    var userId: String? = "",
    var user: User? = null
)

fun DocumentSnapshot.toPost(): Post? {
    return try {
        val postCaption = getString("postCaption")
        val postImage = getString("postImage")
        val postedAt = getTimestamp("postedAt")
        val userId = getString("userId")

        Post(
            postId = id,
            postCaption = postCaption,
            postImage = postImage,
            postedAt = postedAt,
            userId = userId
        )
    } catch (e: FirebaseFirestoreException) {
        Resource.Failure(e.localizedMessage)
        null
    }
}