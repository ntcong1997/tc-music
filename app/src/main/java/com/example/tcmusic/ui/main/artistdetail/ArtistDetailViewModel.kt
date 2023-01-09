package com.example.tcmusic.ui.main.artistdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Result
import com.example.domain.usecase.artist.GetArtistDetailUseCase
import com.example.domain.usecase.player.SetPlaylistAndPlayParams
import com.example.domain.usecase.player.SetPlaylistAndPlayUseCase
import com.example.model.Artist
import com.example.model.Track
import com.example.model.mapper.toTrack
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * Created by TC on 09/01/2023.
 */

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val getArtistDetailUseCase: GetArtistDetailUseCase,
    private val setPlaylistAndPlayUseCase: SetPlaylistAndPlayUseCase
) : ViewModel() {
    private val _artist = MutableStateFlow<Artist?>(null)
    val artist = _artist.asStateFlow()

    val topSongs = _artist.map {
        it?.topSongs?.map {
            it.toTrack()
        } ?: listOf()
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
            val tracks = _artist.value?.topSongs?.map { it.toTrack() } ?: listOf()
            setPlaylistAndPlayUseCase(SetPlaylistAndPlayParams(tracks, tracks.firstOrNull()?.key?.toLong() ?: 0L))
        }
    }

    fun clickTrack(track: Track) {
        viewModelScope.launch {
            setPlaylistAndPlayUseCase(SetPlaylistAndPlayParams(listOf(track), track.key?.toLong() ?: 0L))
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
