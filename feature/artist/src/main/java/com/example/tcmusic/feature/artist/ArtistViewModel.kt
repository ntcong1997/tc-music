package com.example.tcmusic.feature.artist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcmusic.feature.artist.navigation.artistIdArg
import com.example.tcmusic.core.common.result.Result
import com.example.tcmusic.core.domain.usecase.artist.GetArtistDetailUseCase
import com.example.tcmusic.core.domain.usecase.player.*
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by TC on 09/01/2023.
 */

@HiltViewModel
class ArtistViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    observePlayingMediaInfoUseCase: ObservePlayingMediaInfoUseCase,
    observeIsPlayingUseCase: ObserveIsPlayingUseCase,
    private val getArtistDetailUseCase: GetArtistDetailUseCase,
    private val setPlaylistAndPlayUseCase: SetPlaylistAndPlayUseCase,
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

    private val artistId = checkNotNull(savedStateHandle[artistIdArg]) as String

    val artistUiState = artistUiState()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ArtistUiState.Loading)

    private fun artistUiState(): Flow<ArtistUiState> {
        return flow {
            emit(ArtistUiState.Loading)
            val resultGetArtistDetail = getArtistDetailUseCase(artistId)
            if (resultGetArtistDetail is Result.Success) {
                val artist = resultGetArtistDetail.data
                if (artist != null) emit(ArtistUiState.Success(artist))
                else emit(ArtistUiState.Error)
            } else emit(ArtistUiState.Error)
        }
    }

    fun clickPlay(topSongs: List<Track>) {
        viewModelScope.launch {
            setPlaylistAndPlayUseCase(
                SetPlaylistAndPlayParams(
                    topSongs,
                    topSongs.firstOrNull()?.id?.toLong() ?: 0L
                )
            )
        }
    }

    fun clickTrack(track: Track) {
        viewModelScope.launch {
            setPlaylistAndPlayUseCase(
                SetPlaylistAndPlayParams(
                    listOf(track),
                    track.id?.toLong() ?: 0L
                )
            )
        }
    }

    fun clickPlayingMediaInfoPlay() {
        viewModelScope.launch {
            playUseCase()
        }
    }

    fun clickPlayingMediaInfoPause() {
        viewModelScope.launch {
            pauseUseCase()
        }
    }

    fun clickPlayingMediaInfoSkipBackwards() {
        viewModelScope.launch {
            skipBackwardsUseCase()
        }
    }

    fun clickPlayingMediaInfoSkipForward() {
        viewModelScope.launch {
            skipForwardUseCase()
        }
    }
}

sealed interface ArtistUiState {
    data class Success(val artist: Artist) : ArtistUiState
    object Error : ArtistUiState
    object Loading : ArtistUiState
}
