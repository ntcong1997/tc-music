package com.example.tcmusic.feature.track

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcmusic.core.common.result.Result
import com.example.tcmusic.core.common.result.asResult
import com.example.tcmusic.core.domain.usecase.player.*
import com.example.tcmusic.core.domain.usecase.track.GetTrackDetailParams
import com.example.tcmusic.core.domain.usecase.track.GetTrackDetailUseCase
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.feature.track.navigation.trackIdArg
import com.example.tcmusic.feature.track.navigation.trackVersionArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToLong

/**
 * Created by TC on 01/12/2022.
 */

@HiltViewModel
class TrackViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
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
    // Why we need to pass this two arguments while we can get those through ObservePlayingMediaInfoUseCase?
    // Because when we close app media notification maybe still appear so when we click notification
    // those arguments still not init in ObservePlayingMediaInfoUseCase so we must pass those arguments to get track detail
    private val trackId = checkNotNull(savedStateHandle[trackIdArg]) as String
    private val trackVersion = checkNotNull(savedStateHandle[trackVersionArg]) as String

    private val _getTrackDetailParams =
        MutableStateFlow<GetTrackDetailParams?>(GetTrackDetailParams(trackId, trackVersion))
    val track = trackUiState()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), TrackUiState.Loading)

    val trackDuration = observeDurationUseCase(Unit).map {
        if (it is Result.Success) it.data
        else 0L
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0L)

    val trackProgress = observeProgressUseCase(Unit).map {
        if (it is Result.Success) it.data
        else 0L
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0L)

    val trackIsPlaying = observeIsPlayingUseCase(Unit).map {
        if (it is Result.Success) it.data
        else true
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), true)

    init {
        viewModelScope.launch {
            observePlayingMediaInfoUseCase(Unit).collect {
                _getTrackDetailParams.value = if (it is Result.Success) GetTrackDetailParams(
                    it.data?.id,
                    it.data?.version
                ) else null
            }
        }
    }

    private fun trackUiState(): Flow<TrackUiState> {
        return _getTrackDetailParams.map {
            if (it != null) {
                val resultGetTrackDetail = getTrackDetailUseCase(it)
                if (resultGetTrackDetail is Result.Success) resultGetTrackDetail.data
                else null
            } else null
        }
            .asResult()
            .map {
                when (it) {
                    is Result.Success -> {
                        val track = it.data
                        if (track != null) TrackUiState.Success(track)
                        else TrackUiState.Error
                    }
                    is Result.Loading -> TrackUiState.Loading
                    is Result.Error -> TrackUiState.Error
                }
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

    fun progressChange(progress: Float) {
        viewModelScope.launch {
            seekToUseCase(progress.roundToLong() * 1000)
        }
    }
}

sealed interface TrackUiState {
    data class Success(val track: Track) : TrackUiState
    object Error : TrackUiState
    object Loading : TrackUiState
}
