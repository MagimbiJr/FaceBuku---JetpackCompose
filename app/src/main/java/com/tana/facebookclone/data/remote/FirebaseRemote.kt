package com.tana.facebookclone.data.remote

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.storage.FirebaseStorage
import com.tana.facebookclone.domain.modal.*
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseRemote @Inject constructor(
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage,
    private val database: FirebaseFirestore,
) {
    private val usersRef = database.collection("users")
    private val postsRef = database.collection("posts")
    private val currentUser = auth.currentUser
    private val commentsRef = database.collection("comments")
    private val imageRef = storage.reference.child("images").child(System.currentTimeMillis().toString())

    suspend fun signIn(
        phoneOrEmail: String,
        password: String
    ): Flow<Resource<out Response>> = callbackFlow {

        val resultSnapshot =
            auth.signInWithEmailAndPassword(phoneOrEmail, password).addOnCompleteListener { task ->
                val response = if (task.isSuccessful) {
                    val data = Response(success = true, message = "Login successful")
                    Resource.Success(data = data)
                } else {
                    Resource.Failure(message = task.exception?.localizedMessage)
                }
                trySend(response)
            }

        awaitClose { resultSnapshot.isCanceled }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun signUp(
        firstName: String, lastName: String, email: String,
        password: String, birthDate: String, gender: String
    ): Flow<Resource<out Response>> = callbackFlow {
        if (email.isNotBlank() && password.isNotBlank()) {
            val resultSnapshot = auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currUser = task.result.user
                    if (currUser != null) {
                        val registeredUser = User(
                            userId = currUser.uid,
                            firstName = firstName,
                            lastName = lastName, birthDate = birthDate,
                            gender = gender
                        )
                        usersRef.document(currUser.uid).set(registeredUser).addOnCompleteListener { dbTask ->
                            val response = if (dbTask.isSuccessful) {
                                val data = Response(success = true, message = "User registered successfully")
                                Resource.Success(data = data)
                            } else {
                                Resource.Failure(message = dbTask.exception?.localizedMessage)
                            }

                            trySend(response)
                        }
                    }
                } else {
                    Resource.Failure(message = task.exception?.localizedMessage)
                }
            }
            awaitClose { resultSnapshot.isCanceled }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getUser(): Flow<Resource<out User?>> = callbackFlow{
        var userSnapshot: ListenerRegistration? = null
        if (currentUser != null) {
            userSnapshot = usersRef.document(currentUser.uid).addSnapshotListener { value, error ->
                val response = if (value != null) {
                    val user = value.toUser()
                    Resource.Success(data = user)
                } else {
                    Resource.Failure(message = error?.localizedMessage)
                }

                trySend(response)
            }
        } else {
            Resource.Failure(message = "No logged in user")
        }
        awaitClose { userSnapshot?.remove() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun addPost(
        uri: Uri,
        caption: String,
        isNetworkAvailable: Boolean,
    ): Flow<Resource<out Response>> = callbackFlow {
        val resultSnapShot = imageRef.putFile(uri).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (task.isComplete) {
                    imageRef.downloadUrl.addOnSuccessListener { uri ->
                        //val user = auth.currentUser
                        if (currentUser != null) {
                            usersRef.document(currentUser.uid).get().addOnSuccessListener { userSnapshot ->
                                val birthDate = userSnapshot.getString("birthDate")
                                val firstName = userSnapshot.getString("firstName")
                                val lastName = userSnapshot.getString("lastName")
                                val gender = userSnapshot.getString("gender")
                                val userProfilePic = userSnapshot.getString("userProfilePic")
                                val user = User(
                                    userId = currentUser.uid, firstName = firstName, lastName = lastName,
                                    birthDate = birthDate, gender = gender, userProfilePic = userProfilePic
                                )
                                val post = Post(
                                    postImage = uri.toString(),
                                    postCaption = caption,
                                    postedAt = Timestamp.now(),
                                    userId = currentUser.uid,
                                    user = user
                                )
                                database.collection("posts").document(post.postId)
                                    .set(post).addOnCompleteListener {
                                        val response = if (it.isSuccessful) {
                                            val data = Response(
                                                success = true,
                                                message = "Posted successfully"
                                            )

                                            Resource.Success(data = data)
                                        } else {
                                            val exception = if (isNetworkAvailable) {
                                                it.exception?.localizedMessage
                                            } else {
                                                "Please make sure you have active network"
                                            }
                                            Log.d("TAG", "addPost: message is $exception")
                                            Resource.Failure(exception)
                                        }
                                        trySend(response)
                                    }
                            }
                        }

                    }
                }
            }
        }
        awaitClose { resultSnapShot.isCanceled }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun updateCoverPhoto(uri: Uri, user: User?): Flow<Resource<out Response>> = callbackFlow {
        val snapshotListener = imageRef.putFile(uri).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (task.isComplete) {
                    imageRef.downloadUrl.addOnSuccessListener { downloadedUri ->
                        if (currentUser != null) {
                            val updatedUser = User(
                                userId = user?.userId ?: "",
                                firstName = user?.firstName,
                                lastName = user?.lastName,
                                bio = user?.bio,
                                birthDate = user?.birthDate,
                                gender = user?.gender,
                                userProfilePic = user?.userProfilePic,
                                coverPhoto = downloadedUri.toString()
                            )
                            usersRef.document(currentUser.uid).set(updatedUser).addOnCompleteListener { usersRefTask ->
                                val response = if (usersRefTask.isSuccessful) {
                                    val data = Response(
                                        success = true,
                                        message = "Cover updated successfully"
                                    )
                                    Resource.Success(data = data)
                                } else {
                                    Resource.Failure(usersRefTask.exception?.localizedMessage)
                                }
                                trySend(response)
                            }
                        }
                    }
                }
            }
        }
        awaitClose { snapshotListener.isCanceled }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getPosts(): Flow<Resource<out List<Post>>> = callbackFlow {
        val snapShotListener = postsRef.addSnapshotListener { value, error ->
            val response = if (value != null) {
                val data = value.documents.mapNotNull{ postSnapshot->
                    val userId = postSnapshot.getString("userId")
                    val post: Post? = postSnapshot.toPost()
                    if(userId != null){
                        usersRef.document(userId).get().addOnSuccessListener{ userSnapchat ->
                            if( userSnapchat.data != null) {
                                post?.user = userSnapchat?.toUser()
                            }
                        }
                    }
                    post
                }.sortedBy { it.postedAt }

                Resource.Success(data = data)
            } else {
                Resource.Failure(error?.localizedMessage)
            }
            trySend(response)
        }
        awaitClose { snapShotListener.remove() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getPostsByUser(userId: String?): Flow<Resource<out List<Post>>> = callbackFlow {
        val snapshotListener = postsRef.whereEqualTo("userId", userId).addSnapshotListener { value, error ->
            val response = if (value != null) {
                val data = value.documents.mapNotNull { postSnapshot ->
                    postSnapshot.toPost()
//                    val post = postSnapshot.toPost()
//                    usersRef.document(userId).get().addOnSuccessListener { userSnapshot ->
//                        post?.user = userSnapshot.toUser()
//                    }
//                    post
                }
                Log.d("TAG", "getPostsByUser: posts are $data")
                Log.d("TAG", "getPostsByUser: User id is $userId")
                Resource.Success(data = data)
            } else {
                Resource.Failure(message = error?.localizedMessage)
            }
            trySend(response)
        }

        awaitClose { snapshotListener.remove() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun addComment(value: String, postId: String): Flow<Resource<out Response>> = callbackFlow {
        var comment: Comment? = null

        if (currentUser != null) {
            comment = Comment(
                comment = value,
                userId = currentUser.uid,
                postId = postId
            )
        }
        val snapShotListener =
            comment?.commentId?.let {
                commentsRef.document(it).set(comment).addOnCompleteListener { task ->
                    val response = if (task.isSuccessful) {
                        val data = Response(
                            success = true,
                            message = "Commented on post"
                        )
                        Resource.Success(data = data)
                    } else {
                        Resource.Failure(message = task.exception?.localizedMessage)
                    }
                    trySend(response)
                }
            }

        awaitClose { snapShotListener?.isCanceled }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getComments(postId: String): Flow<Resource<out List<Comment>>> = callbackFlow {
        //val snapshotListener = commentsRef.addSnapshotListener { value, error ->
        val snapshotListener = commentsRef.whereEqualTo("postId", postId).addSnapshotListener { value, error ->
            val response = if (value != null) {
                val data = value.mapNotNull { it.toComment() }
                    .sortedBy { it.commentedAt?.seconds }
                Resource.Success(data = data)
            } else {
                Resource.Failure(message = error?.localizedMessage)
            }
            trySend(response)
        }

        awaitClose { snapshotListener.remove() }
    }

}