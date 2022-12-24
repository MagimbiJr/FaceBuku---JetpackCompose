package com.tana.facebookclone.domain.modal

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.tana.facebookclone.utils.Resource
import java.io.IOException
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
data class Comment(
    val commentId: String = UUID.randomUUID().toString(),
    val comment: String? = "",
    val userId: String? = "",
    val postId: String? = "",
    val commentedAt: Timestamp? = Timestamp.now()
)


@RequiresApi(Build.VERSION_CODES.O)
fun DocumentSnapshot.toComment(): Comment? {
    return try {
        val comment = getString("comment")
        val userId = getString("userId")
        val postId = getString("postId")
        val commentedAt = getTimestamp("commentedAt")

        Comment(
            commentId = id, comment = comment,
            userId = userId, postId = postId,
            commentedAt = commentedAt
        )
    } catch (e: FirebaseFirestoreException) {
        Resource.Failure(message = e.localizedMessage)
        null
    } catch (e: IOException) {
        Resource.Failure(message = e.localizedMessage)
        null
    }
}