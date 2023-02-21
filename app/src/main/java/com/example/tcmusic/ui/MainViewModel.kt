package com.example.tcmusic.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcmusic.core.common.result.Result
import com.example.tcmusic.core.domain.usecase.player.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by TC on 09/10/2022.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    observePlayingMediaUseCase: ObservePlayingMediaUseCase,
    observeIsPlayingUseCase: ObserveIsPlayingUseCase,
    private val playUseCase: PlayUseCase,
    private val pauseUseCase: PauseUseCase,
    private val skipBackwardsUseCase: SkipBackwardsUseCase,
    private val skipForwardUseCase: SkipForwardUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _navigateToTrackDetail = Channel<DataTrackDetail>(Channel.CONFLATED)
    val navigateToTrackDetail = _navigateToTrackDetail.receiveAsFlow()

    val playingMediaInfo = observePlayingMediaUseCase(Unit).map {
        if (it is Result.Success) it.data
        else null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    val isPlaying = observeIsPlayingUseCase(Unit).map {
        if (it is Result.Success) it.data
        else true
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), true)

    init {
        viewModelScope.launch {
            delay(2000L)
            _isLoading.value = false
        }
    }

    fun clickPlay() {
        viewModelScope.launch {
            playUseCase()
        }
    }

    fun clickPause() {
        viewModelScope.launch {
            pauseUseCase()
        }
    }

    fun clickSkipBackwards() {
        viewModelScope.launch {
            skipBackwardsUseCase()
        }
    }

    fun clickSkipForward() {
        viewModelScope.launch {
            skipForwardUseCase()
        }
    }

    fun openTrackDetail(trackId: String?, version: String?) {
        _navigateToTrackDetail.trySend(DataTrackDetail(trackId, version))
    }
}

data class DataTrackDetail(
    val trackId: String?,
    val trackVersion: String?
)
