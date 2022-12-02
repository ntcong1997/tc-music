package com.example.tcmusic.ui.main.main.home.hiphoprapchart

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.domain.Result
import com.example.domain.model.GenreCode
import com.example.domain.usecase.chart.LoadWorldChartByGenreParams
import com.example.domain.usecase.chart.LoadWorldChartByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by TC on 25/11/2022.
 */

@HiltViewModel
class HipHopRapChartViewModel @Inject constructor(
    loadWorldChartByGenreUseCase: LoadWorldChartByGenreUseCase
) : ViewModel() {
    val tracks = loadWorldChartByGenreUseCase(LoadWorldChartByGenreParams(GenreCode.HIP_HOP_RAP, PAGE_SIZE)).map {
        if (it is Result.Success) it.data
        else PagingData.empty()
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}