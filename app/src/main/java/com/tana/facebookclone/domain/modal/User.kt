package com.tana.facebookclone.domain.modal

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.tana.facebookclone.utils.Resource
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class User(
    val userId: String = "",
    val firstName: String? = "",
    val lastName: String? = "",
    val bio: String? = "",
    val birthDate: String? = LocalDate.now().toString(),
    val gender: String? = "",
    val userProfilePic: String? = "",
    val coverPhoto: String? = ""
)

@RequiresApi(Build.VERSION_CODES.O)
fun DocumentSnapshot.toUser(): User? {
    return try {
        val firstName = getString("firstName")
        val lastName = getString("lastName")
        val birthDate = getString("birthDate")
        val bio = getString("bio")
        val gender = getString("gender")
        val userProfilePic = getString("userProfilePic")
        val coverPhoto = getString("coverPhoto")

        User(
            userId = id,
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            bio = bio,
            gender = gender,
            userProfilePic = userProfilePic,
            coverPhoto = coverPhoto
        )
    } catch (e: FirebaseFirestoreException) {
        Resource.Failure(e.localizedMessage)
        null
    }
}
