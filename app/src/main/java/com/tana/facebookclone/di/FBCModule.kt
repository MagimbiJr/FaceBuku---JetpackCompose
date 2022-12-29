package com.tana.facebookclone.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.tana.facebookclone.data.remote.FirebaseRemote
import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.repository.FCBRepositoryImpl
import com.tana.facebookclone.domain.use_cases.add_post_use_case.AddPostUseCase
import com.tana.facebookclone.domain.use_cases.add_post_use_case.UpdateCoverUseCase
import com.tana.facebookclone.domain.use_cases.add_post_use_case.UpdateProfilePhotoUseCase
import com.tana.facebookclone.domain.use_cases.comments.add_comment.AddCommentUseCase
import com.tana.facebookclone.domain.use_cases.comments.get_comments.GetCommentsUseCase
import com.tana.facebookclone.domain.use_cases.get_user_use_case.GetUserUseCase
import com.tana.facebookclone.domain.use_cases.auth_use_cases.SignInUseCase
import com.tana.facebookclone.domain.use_cases.auth_use_cases.SignUpUseCase
import com.tana.facebookclone.domain.use_cases.get_posts.GetPostsByUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FBCModule {

    @Provides
    @Singleton
    fun provideAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideDatabase(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseApi(
        auth: FirebaseAuth,
        storage: FirebaseStorage,
        database: FirebaseFirestore
    ): FirebaseRemote = FirebaseRemote(auth = auth, storage = storage, database = database)

    @Provides
    @Singleton
    fun provideSignInRepository(
        api: FirebaseRemote
    ): FBCRepository =
        FCBRepositoryImpl(api = api)

    @Provides
    @Singleton
    fun providesSignUpUseCase(
        repository: FBCRepository
    ): SignUpUseCase =
        SignUpUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideSignInUseCase(repository: FBCRepository): SignInUseCase =
        SignInUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideAddPostUseCase(repository: FBCRepository): AddPostUseCase =
        AddPostUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideUpdateCoverUseCase(repository: FBCRepository): UpdateCoverUseCase =
        UpdateCoverUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideUpdateProfilePhotoUseCase(repository: FBCRepository): UpdateProfilePhotoUseCase =
        UpdateProfilePhotoUseCase(repository = repository)


    @Provides
    @Singleton
    fun provideGetUserUseCase(repository: FBCRepository): GetUserUseCase =
        GetUserUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideGetPostsByUserUseCase(repository: FBCRepository): GetPostsByUserUseCase =
        GetPostsByUserUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideAddCommentUseCase(repository: FBCRepository): AddCommentUseCase =
        AddCommentUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideGetCommentUseCase(repository: FBCRepository): GetCommentsUseCase =
        GetCommentsUseCase(repository = repository)
}