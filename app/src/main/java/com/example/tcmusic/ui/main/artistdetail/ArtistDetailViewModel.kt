package com.example.tcmusic.ui.main.artistdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Result
import com.example.domain.usecase.artist.GetArtistDetailUseCase
import com.example.domain.usecase.player.*
import com.example.model.Artist
import com.example.model.Track
import com.example.tcmusic.util.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * Created by TC on 09/01/2023.
 */

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
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
    }.stateIn(viewModelScope, WhileViewSubscribed, null)

    val isPlaying = observeIsPlayingUseCase(Unit).map {
        if (it is Result.Success) it.data
        else true
    }.stateIn(viewModelScope, WhileViewSubscribed, true)

    private val _artist = MutableStateFlow<Artist?>(null)
    val artist = _artist.asStateFlow()

    val topSongs = _artist.map {
        it?.topSongs ?: listOf()
    }

    private val _showLoading = MutableStateFlow(false)
    val showLoading: StateFlow<Boolean> = _showLoading

    private val _showError = MutableStateFlow<Error?>(null)
    val showError: StateFlow<Error?> = _showError

    fun getArtistDetail(artistId: String?) {
        viewModelScope.launch {
            _showLoading.value = true
            val resultGetArtistDetail = getArtistDetailUseCase(artistId)
            if (resultGetArtistDetail is Result.Success) {
                _artist.value = resultGetArtistDetail.data
            } else if (resultGetArtistDetail is Result.Error) {
                when (val throwable = resultGetArtistDetail.throwable) {
                    is HttpException -> {
                        // TODO: handle error
                    }
                    is IOException -> _showError.value = Error.NETWORK
                    else -> _showError.value = Error.UNKNOWN
                }
            } else _showError.value = Error.UNKNOWN
            _showLoading.value = false
        }
    }

    fun clickPlay() {
        viewModelScope.launch {
            val tracks = _artist.value?.topSongs ?: listOf()
            setPlaylistAndPlayUseCase(SetPlaylistAndPlayParams(tracks, tracks.firstOrNull()?.id?.toLong() ?: 0L))
        }
    }

    fun clickTrack(track: Track) {
        viewModelScope.launch {
            setPlaylistAndPlayUseCase(SetPlaylistAndPlayParams(listOf(track), track.id?.toLong() ?: 0L))
        }
    }

    fun clickPlayingMediaInfoPlay() {
        viewModelScope.launch {
            playUseCase(Unit)
        }
    }

    fun clickPlayingMediaInfoPause() {
        viewModelScope.launch {
            pauseUseCase(Unit)
        }
    }

    fun clickPlayingMediaInfoSkipBackwards() {
        viewModelScope.launch {
            skipBackwardsUseCase(Unit)
        }
    }

    fun clickPlayingMediaInfoSkipForward() {
        viewModelScope.launch {
            skipForwardUseCase(Unit)
        }
    }

    fun dismissError() {
        _showError.value = null
    }
}

enum class Error {
    NETWORK,
    UNKNOWN
}
