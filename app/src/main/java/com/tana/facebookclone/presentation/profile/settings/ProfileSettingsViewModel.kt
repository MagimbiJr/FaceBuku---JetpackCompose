package com.tana.facebookclone.presentation.profile.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tana.facebookclone.utils.AppEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSettingsViewModel @Inject constructor() : ViewModel() {
    private val _appEvents = Channel<AppEvents>()
    val appEvents = _appEvents.receiveAsFlow()


    fun onBackArrowClicked() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.PopBack)
        }
    }

    fun onCopyLinkClicked() {
        viewModelScope.launch {
            _appEvents.send(AppEvents.ShowSnackBar("Copying link is not implemented yet"))
        }
    }
}