package com.example.tcmusic.feature.artist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcmusic.core.common.result.Result
import com.example.tcmusic.core.domain.usecase.artist.GetArtistDetailUseCase
import com.example.tcmusic.core.domain.usecase.player.*
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.feature.artist.navigation.artistIdArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by TC on 09/01/2023.
 */

@HiltViewModel
class ArtistViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getArtistDetailUseCase: GetArtistDetailUseCase,
    private val setPlaylistAndPlayUseCase: SetPlaylistAndPlayUseCase
) : ViewModel() {
    val artistId = checkNotNull(savedStateHandle[artistIdArg]) as String

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
}

sealed interface ArtistUiState {
    data class Success(val artist: Artist) : ArtistUiState
    object Error : ArtistUiState
    object Loading : ArtistUiState
}
