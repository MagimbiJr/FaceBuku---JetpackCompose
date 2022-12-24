package com.tana.facebookclone.domain.use_cases.add_post_use_case

import android.net.Uri
import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.modal.Response
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddPostUseCase @Inject constructor(
    private val repository: FBCRepository
) {

    suspend operator fun invoke(uri: Uri, caption: String, isNetworkAvailable: Boolean,): Flow<Resource<out Response>> {
        return repository.addPost(uri = uri, caption = caption, isNetworkAvailable = isNetworkAvailable)
    }

}