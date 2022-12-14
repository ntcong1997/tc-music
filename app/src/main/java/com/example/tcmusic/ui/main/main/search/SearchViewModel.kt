package com.example.tcmusic.ui.main.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.Result
import com.example.domain.usecase.artist.SearchArtistsUseCase
import com.example.domain.usecase.player.SetPlaylistAndPlayParams
import com.example.domain.usecase.player.SetPlaylistAndPlayUseCase
import com.example.domain.usecase.track.SearchTracksUseCase
import com.example.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by TC on 05/01/2023.
 */

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchTracksUseCase: SearchTracksUseCase,
    private val searchArtistsUseCase: SearchArtistsUseCase,
    private val setPlaylistAndPlayUseCase: SetPlaylistAndPlayUseCase
) : ViewModel() {
    private val _textSearch = MutableStateFlow("")
    val textSearch = _textSearch.asStateFlow()

    val tracks = _textSearch
        .debounce {
            if (it.length > 1) 500L
            else 0L
        }
        .distinctUntilChanged()
        .flatMapLatest {
            if (it.length > 1) searchTracksUseCase(it)
            else flow { emit(Result.Error(Exception("not enough character"))) }
        }
        .map {
            if (it is Result.Success) it.data
            else PagingData.empty()
        }
        .cachedIn(viewModelScope)

    val artists = _textSearch
        .debounce {
            if (it.length > 1) 500L
            else 0L
        }
        .distinctUntilChanged()
        .flatMapLatest {
            if (it.length > 1) searchArtistsUseCase(it)
            else flow { emit(Result.Error(Exception("not enough character"))) }
        }
        .map {
            if (it is Result.Success) it.data
            else PagingData.empty()
        }
        .cachedIn(viewModelScope)

    fun search(text: String) {
        _textSearch.value = text
    }

    fun clickTrack(track: Track) {
        viewModelScope.launch {
            setPlaylistAndPlayUseCase(SetPlaylistAndPlayParams(listOf(track), track.key?.toLong() ?: 0L))
        }
    }
}
