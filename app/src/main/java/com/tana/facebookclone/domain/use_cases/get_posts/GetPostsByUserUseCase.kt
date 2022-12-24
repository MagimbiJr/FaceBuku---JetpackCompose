package com.tana.facebookclone.domain.use_cases.get_posts

import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.modal.Post
import com.tana.facebookclone.domain.modal.User
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsByUserUseCase @Inject constructor(
    private val repository: FBCRepository
) {

    suspend operator fun invoke(userId: String?): Flow<Resource<out List<Post>>> {
        return repository.getPostByUser(userId = userId)
    }

}