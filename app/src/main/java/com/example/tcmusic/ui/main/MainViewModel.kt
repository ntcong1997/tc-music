package com.example.tcmusic.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Created by TC on 09/10/2022.
 */

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _navigateToTrackDetail = Channel<String?>(Channel.CONFLATED)
    val navigateToTrackDetail = _navigateToTrackDetail.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(2000L)
            _isLoading.value = false
        }
    }

    fun openTrackDetail(trackId: String?) {
        _navigateToTrackDetail.trySend(trackId)
    }
}
