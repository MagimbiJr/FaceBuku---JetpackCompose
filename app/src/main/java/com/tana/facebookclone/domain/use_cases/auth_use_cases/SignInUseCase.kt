package com.tana.facebookclone.domain.use_cases.auth_use_cases

import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.modal.Response
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: FBCRepository
) {

    suspend operator fun invoke(
        phoneOrEmail: String,
        password: String
    ): Flow<Resource<out Response>> {
        return repository.signIn(
            phoneOrEmail = phoneOrEmail,
            password = password
        )
    }

}