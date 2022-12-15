package com.example.tcmusic.ui.main.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Result
import com.example.domain.usecase.player.*
import com.example.tcmusic.util.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Created by TC on 14/12/2022.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
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
    }.stateIn(viewModelScope, WhileViewSubscribed, null)

    val isPlaying = observeIsPlayingUseCase(Unit).map {
        if (it is Result.Success) it.data
        else true
    }.stateIn(viewModelScope, WhileViewSubscribed, true)

    fun clickPlay() {
        viewModelScope.launch {
            playUseCase(Unit)
        }
    }

    fun clickPause() {
        viewModelScope.launch {
            pauseUseCase(Unit)
        }
    }

    fun clickSkipBackwards() {
        viewModelScope.launch {
            skipBackwardsUseCase(Unit)
        }
    }

    fun clickSkipForward() {
        viewModelScope.launch {
            skipForwardUseCase(Unit)
        }
    }
}
