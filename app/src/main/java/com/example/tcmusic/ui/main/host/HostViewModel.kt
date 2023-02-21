package com.example.tcmusic.ui.main.host

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcmusic.core.common.result.Result
import com.example.tcmusic.core.domain.usecase.player.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by TC on 14/12/2022.
 */

@HiltViewModel
class HostViewModel @Inject constructor(
    observePlayingMediaInfoUseCase: ObservePlayingMediaInfoUseCase,
    observeIsPlayingUseCase: ObserveIsPlayingUseCase,
    private val playUseCase: PlayUseCase,
    private val pauseUseCase: PauseUseCase,
    private val skipBackwardsUseCase: SkipBackwardsUseCase,
    private val skipForwardUseCase: SkipForwardUseCase
) : ViewModel() {
    val playingMediaInfo = observePlayingMediaInfoUseCase(Unit).map {
        if (it is Result.Success) it.data
        else null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    val isPlaying = observeIsPlayingUseCase(Unit).map {
        if (it is Result.Success) it.data
        else true
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), true)

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
}
