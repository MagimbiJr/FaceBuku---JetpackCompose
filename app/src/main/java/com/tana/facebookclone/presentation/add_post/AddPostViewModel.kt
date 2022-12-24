package com.tana.facebookclone.presentation.add_post

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.tana.facebookclone.domain.use_cases.add_post_use_case.AddPostUseCase
import com.tana.facebookclone.domain.use_cases.get_user_use_case.GetUserUseCase
import com.tana.facebookclone.utils.AppEvents
import com.tana.facebookclone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val addPostUseCase: AddPostUseCase,
    private val getUserUseCase: GetUserUseCase,
    application: Application
) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(AddPostUiState())
    val uiState = _uiState.asStateFlow()
    private val _appEvents = Channel<AppEvents>()
    val appEvents = _appEvents.receiveAsFlow()
    var isUploadingFailed = false

    init {
        viewModelScope.launch {
            if (FirebaseAuth.getInstance().currentUser == null) {
                _appEvents.send(AppEvents.SignInRequired("signin"))
            } else {
                getUserUseCase().collectLatest { response ->
                    when(response) {
                        is Resource.Success -> {
                            _uiState.value = _uiState.value.copy(user = response.data)
                            Log.d("TAG", "initBlock: User is ${response.data}")
                        }
                        is Resource.Failure -> {
                            _uiState.value = _uiState.value.copy(errorMessage = response.message)
                        }
                        is Resource.Loading -> Unit
                    }
                }
            }
        }
    }

    fun addPost() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                loading = true
            )
            addPostUseCase(
                uri = _uiState.value.uri!!,
                caption = _uiState.value.caption,
                isNetworkAvailable = isNetworkAvailable(application = getApplication())
            ).collectLatest { response ->
                when(response) {
                    is Resource.Success -> {
                        _appEvents.send(AppEvents.ShowSnackBar(response.data.message))
                        _appEvents.send(AppEvents.Navigate("home"))
                    }
                    is Resource.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = response.message,
                            loading = false
                        )
                        isUploadingFailed = true
                    }
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }
                }
            }
            _uiState.value = _uiState.value.copy(loading = false)
        }
    }

    private fun isNetworkAvailable(application: Context): Boolean {
        var result = false
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when(type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }

    fun onCaptionChange(caption: String) {
        _uiState.value = _uiState.value.copy(caption = caption)
    }

    fun updateUri(uri: Uri?) {
        _uiState.value = _uiState.value.copy(uri = uri)
    }

    fun navigateBack() {
        viewModelScope.launch {
            if (_uiState.value.uri == null && _uiState.value.caption.isBlank()) {
                _appEvents.send(AppEvents.PopBack)
            } else {
                isUploadingFailed = true
            }
        }
    }

    fun onAddMoreClick() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar(message = "This feature is not available"))
        }
    }
}