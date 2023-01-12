package com.tana.facebookclone.presentation.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class EditProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState = _uiState.asStateFlow()
    private val _appEvents = Channel<AppEvents>()
    val appEvents = _appEvents.receiveAsFlow()

    init {
        viewModelScope.launch {
            getUserUseCase().collectLatest { response ->
                when(response) {
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            user = response.data
                        )
                    }
                    is Resource.Failure -> {
                        _uiState.value = _uiState.value.copy(
                            errorMessage = response.message ?: ""
                        )
                    }
                    is Resource.Loading -> Unit
                }
            }
        }
    }

    fun backButtonClicked() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.PopBack)
        }
    }

    fun onAddOrEditProfileClick() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar("This feature is not available for now"))
        }
    }

    fun onAddOrEditCoverClick() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar("This feature is not available for now"))
        }
    }

    fun onCreateAvatarClick() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar("Avatar creation is not available"))
        }
    }

    fun onAddOrEditBioClick() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar("Avatar creation is not available"))
        }
    }

    fun onAddOrEditDetailsClick() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar("Avatar creation is not available"))
        }
    }

    fun onAddOrEditFeaturedClick() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar("Avatar creation is not available"))
        }
    }

    fun onAddOrEditHobbiesClick() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar("Avatar creation is not available"))
        }
    }

    fun onAddOrEditLinksClick() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar("Avatar creation is not available"))
        }
    }

    fun onEditInfoClick() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar("Avatar creation is not available"))
        }
    }
}