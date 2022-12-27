package com.tana.facebookclone.domain.repository

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.tana.facebookclone.data.remote.FirebaseRemote
import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.modal.*
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FCBRepositoryImpl @Inject constructor(
    private val api: FirebaseRemote
) : FBCRepository {

    override suspend fun signIn(
        phoneOrEmail: String,
        password: String
    ): Flow<Resource<out Response>> {
        return api.signIn(
            phoneOrEmail = phoneOrEmail,
            password = password
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        birthDate: String,
        gender: String
    ): Flow<Resource<out Response>> {
        return api.signUp(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            birthDate = birthDate,
            gender = gender
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getUser(): Flow<Resource<out User?>> {
        return api.getUser()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun addPost(
        uri: Uri,
        caption: String,
        isNetworkAvailable: Boolean,
    ): Flow<Resource<out Response>> {
        return api.addPost(
            uri = uri,
            caption = caption,
            isNetworkAvailable = isNetworkAvailable
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateCoverPhoto(uri: Uri, user: User?): Flow<Resource<out Response>> {
        return api.updateCoverPhoto(uri = uri, user = user)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun updateProfilePhoto(uri: Uri, user: User?): Flow<Resource<out Response>> {
        return api.updateProfilePhoto(uri = uri, user = user)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPosts(): Flow<Resource<out List<Post>>> {
        return api.getPosts()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getPostByUser(userId: String?): Flow<Resource<out List<Post>>> {
        return api.getPostsByUser(userId = userId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun addComment(comment: String, postId: String): Flow<Resource<out Response>> {
        return api.addComment(value = comment, postId = postId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getComments(postId: String): Flow<Resource<out List<Comment>>> {
        return api.getComments(postId = postId)
    }

}