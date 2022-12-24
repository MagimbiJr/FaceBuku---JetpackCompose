package com.tana.facebookclone.domain.use_cases.auth_use_cases

import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.modal.Response
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: FBCRepository
) {

    suspend operator fun invoke(
        firstName: String, lastName: String, email: String,
        password: String, birthDate: String, gender: String
    ): Flow<Resource<out Response>> {
        return repository.signUp(
            firstName = firstName,
            lastName = lastName,
            email = email, password = password,
            birthDate = birthDate, gender = gender
        )
    }

}