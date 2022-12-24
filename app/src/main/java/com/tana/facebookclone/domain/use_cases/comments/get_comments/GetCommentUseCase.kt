package com.tana.facebookclone.domain.use_cases.comments.get_comments

import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.modal.Comment
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: FBCRepository
) {

    suspend operator fun invoke(postId: String): Flow<Resource<out List<Comment>>> {
        return repository.getComments(postId = postId)
    }

}