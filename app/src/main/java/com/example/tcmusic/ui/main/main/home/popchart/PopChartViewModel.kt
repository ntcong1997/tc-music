package com.example.tcmusic.ui.main.main.home.popchart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.Result
import com.example.domain.usecase.chart.LoadWorldChartByGenreParams
import com.example.domain.usecase.chart.LoadWorldChartByGenreUseCase
import com.example.domain.usecase.chart.RefreshWorldChartByGenreUseCase
import com.example.domain.usecase.player.SetPlaylistAndPlayParams
import com.example.domain.usecase.player.SetPlaylistAndPlayUseCase
import com.example.model.GenreCode
import com.example.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by TC on 22/11/2022.
 */

@HiltViewModel
class PopChartViewModel @Inject constructor(
    loadWorldChartByGenreUseCase: LoadWorldChartByGenreUseCase,
    private val refreshWorldChartByGenreUseCase: RefreshWorldChartByGenreUseCase,
    private val setPlaylistAndPlayUseCase: SetPlaylistAndPlayUseCase
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    val tracks = loadWorldChartByGenreUseCase(LoadWorldChartByGenreParams(GenreCode.POP)).map {
        if (it is Result.Success) it.data
        else PagingData.empty()
    }.cachedIn(viewModelScope)

    fun clickTrack(track: Track) {
        viewModelScope.launch {
            val startPlayingId = try {
                track.key?.toLong() ?: 0L
            } catch (e: Exception) {
                e.printStackTrace()
                0L
            }
            setPlaylistAndPlayUseCase(SetPlaylistAndPlayParams(listOf(track), startPlayingId))
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshWorldChartByGenreUseCase(GenreCode.POP)
            _isRefreshing.value = false
        }
    }
}
