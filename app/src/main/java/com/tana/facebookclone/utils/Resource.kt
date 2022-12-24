package com.tana.facebookclone.utils

sealed class Resource<T>(data: T? = null, message: String? = null) {
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>(data = data)
    data class Failure(val message: String?) : Resource<Nothing>(message = message)
}
