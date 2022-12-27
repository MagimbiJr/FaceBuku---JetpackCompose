package com.tana.facebookclone.data.repository

import android.net.Uri
import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.domain.modal.Response
import com.tana.facebookclone.domain.modal.Post
import com.tana.facebookclone.domain.modal.User
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FBCRepository {

    suspend fun signIn(phoneOrEmail: String, password: String): Flow<Resource<out Response>>

    suspend fun signUp(
        firstName: String, lastName: String, email: String,
        password: String, birthDate: String, gender: String
    ): Flow<Resource<out Response>>

    suspend fun getUser(): Flow<Resource<out User?>>

    suspend fun addPost(
        uri: Uri,
        caption: String,
        isNetworkAvailable: Boolean,
    ): Flow<Resource<out Response>>

    suspend fun updateCoverPhoto(uri: Uri, user: User?): Flow<Resource<out Response>>

    suspend fun updateProfilePhoto(uri: Uri, user: User?): Flow<Resource<out Response>>

    suspend fun getPosts(): Flow<Resource<out List<Post>>>

    suspend fun getPostByUser(userId: String?): Flow<Resource<out List<Post>>>

    suspend fun addComment(comment: String, postId: String): Flow<Resource<out Response>>

    suspend fun getComments(postId: String): Flow<Resource<out List<Comment>>>

}