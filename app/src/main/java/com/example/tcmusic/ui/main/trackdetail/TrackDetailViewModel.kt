package com.example.tcmusic.ui.main.trackdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Result
import com.example.domain.usecase.player.*
import com.example.domain.usecase.track.GetTrackDetailParams
import com.example.domain.usecase.track.GetTrackDetailUseCase
import com.example.tcmusic.util.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.math.roundToLong
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by TC on 01/12/2022.
 */

@HiltViewModel
class TrackDetailViewModel @Inject constructor(
    observePlayingMediaInfoUseCase: ObservePlayingMediaInfoUseCase,
    private val getTrackDetailUseCase: GetTrackDetailUseCase,
    observeDurationUseCase: ObserveDurationUseCase,
    observeProgressUseCase: ObserveProgressUseCase,
    observeIsPlayingUseCase: ObserveIsPlayingUseCase,
    private val playUseCase: PlayUseCase,
    private val pauseUseCase: PauseUseCase,
    private val skipBackwardsUseCase: SkipBackwardsUseCase,
    private val skipForwardUseCase: SkipForwardUseCase,
    private val seekToUseCase: SeekToUseCase
) : ViewModel() {
    private val _track = MutableStateFlow<GetTrackDetailParams?>(null)
    val track = _track.map {
        if (it != null) {
            val resultGetTrackDetail = getTrackDetailUseCase(it)
            if (resultGetTrackDetail is Result.Success) resultGetTrackDetail.data
            else null
        }
        else null
    }.stateIn(viewModelScope, WhileViewSubscribed, null)

    val trackDuration = observeDurationUseCase(Unit).map {
        if (it is Result.Success) it.data
        else 0L
    }.stateIn(viewModelScope, WhileViewSubscribed, 0L)

    val trackProgress = observeProgressUseCase(Unit).map {
        if (it is Result.Success) it.data
        else 0L
    }.stateIn(viewModelScope, WhileViewSubscribed, 0L)

    val trackIsPlaying = observeIsPlayingUseCase(Unit).map {
        if (it is Result.Success) it.data
        else true
    }.stateIn(viewModelScope, WhileViewSubscribed, true)

    init {
        viewModelScope.launch {
            observePlayingMediaInfoUseCase(Unit).collect {
                _track.value = if (it is Result.Success) GetTrackDetailParams(it.data?.id, it.data?.version) else null
            }
        }
    }

    fun track(trackId: String?, trackVersion: String?) {
        _track.value = GetTrackDetailParams(trackId, trackVersion)
    }

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

    fun progressChange(progress: Float) {
        viewModelScope.launch {
            seekToUseCase(progress.roundToLong() * 1000)
        }
    }
}
