package com.tana.facebookclone.domain.use_cases.comments.add_comment

import com.tana.facebookclone.data.repository.FBCRepository
import com.tana.facebookclone.domain.modal.Response
import com.tana.facebookclone.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCommentUseCase @Inject constructor(
    private val repository: FBCRepository
) {

    suspend operator fun invoke(comment: String, postId: String): Flow<Resource<out Response>> {
        return repository.addComment(comment = comment, postId = postId)
    }

}