package com.example.tcmusic.feature.home.hiphoprapchart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tcmusic.core.common.GenreCode
import com.example.tcmusic.core.common.result.Result
import com.example.tcmusic.core.domain.usecase.chart.LoadWorldChartByGenreParams
import com.example.tcmusic.core.domain.usecase.chart.LoadWorldChartByGenreUseCase
import com.example.tcmusic.core.domain.usecase.chart.RefreshWorldChartByGenreUseCase
import com.example.tcmusic.core.domain.usecase.player.SetPlaylistAndPlayParams
import com.example.tcmusic.core.domain.usecase.player.SetPlaylistAndPlayUseCase
import com.example.tcmusic.core.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by TC on 25/11/2022.
 */

@HiltViewModel
class HipHopRapChartViewModel @Inject constructor(
    loadWorldChartByGenreUseCase: LoadWorldChartByGenreUseCase,
    private val refreshWorldChartByGenreUseCase: RefreshWorldChartByGenreUseCase,
    private val setPlaylistAndPlayUseCase: SetPlaylistAndPlayUseCase
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    val tracks = loadWorldChartByGenreUseCase(
        LoadWorldChartByGenreParams(
            GenreCode.HIP_HOP_RAP.value
        )
    ).map {
        if (it is Result.Success) it.data
        else PagingData.empty()
    }.cachedIn(viewModelScope)

    fun clickTrack(track: Track) {
        viewModelScope.launch {
            val startPlayingId = try {
                track.id?.toLong() ?: 0L
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
            refreshWorldChartByGenreUseCase(GenreCode.HIP_HOP_RAP.value)
            _isRefreshing.value = false
        }
    }
}
