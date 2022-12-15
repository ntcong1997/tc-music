package com.example.tcmusic.ui.main.trackdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Result
import com.example.domain.usecase.player.*
import com.example.domain.usecase.track.GetTrackDetailUseCase
import com.example.model.Track
import com.example.tcmusic.util.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToLong
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Created by TC on 01/12/2022.
 */

@HiltViewModel
class TrackDetailViewModel @Inject constructor(
    private val getTrackDetailUseCase: GetTrackDetailUseCase,
    observeDurationUseCase: ObserveDurationUseCase,
    observeProgressUseCase: ObserveProgressUseCase,
    observeIsPlayingUseCase: ObserveIsPlayingUseCase,
    private val playUseCase: PlayUseCase,
    private val pauseUseCase: PauseUseCase,
    private val seekToUseCase: SeekToUseCase
) : ViewModel() {
    private val _track = MutableStateFlow<Track?>(null)
    val track = _track.asStateFlow()

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

    fun getTrackDetail(trackId: String?) {
        viewModelScope.launch {
            val resultGetTrackDetail = getTrackDetailUseCase(trackId)
            if (resultGetTrackDetail is Result.Success) {
                _track.value = resultGetTrackDetail.data
            }
        }
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

    fun progressChange(progress: Float) {
        viewModelScope.launch {
            seekToUseCase(progress.roundToLong() * 1000)
        }
    }
}
