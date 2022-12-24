package com.tana.facebookclone.domain.use_cases.get_user_use_case

import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.modal.User
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: FBCRepository
) {

    suspend operator fun invoke(): Flow<Resource<out User?>> {
        return repository.getUser()
    }

}