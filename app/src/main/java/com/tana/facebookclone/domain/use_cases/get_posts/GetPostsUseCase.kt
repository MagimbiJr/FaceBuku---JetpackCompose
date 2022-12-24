package com.tana.facebookclone.domain.use_cases.get_posts

import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.modal.Post
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: FBCRepository
) {

    suspend operator fun invoke(): Flow<Resource<out List<Post>>> {
        return repository.getPosts()
    }

}