package com.tana.facebookclone.domain.use_cases.add_post_use_case

import android.net.Uri
import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.modal.Response
import com.tana.facebookclone.domain.modal.User
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateCoverUseCase @Inject constructor(
    private val repository: FBCRepository
) {


    suspend operator fun invoke(uri: Uri, user: User?): Flow<Resource<out Response>> {
        return repository.updateCoverPhoto(uri = uri, user = user)
    }

}