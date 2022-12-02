package com.example.tcmusic.ui.main.main.home.worldchart

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.domain.Result
import com.example.domain.usecase.chart.LoadWorldChartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by TC on 21/11/2022.
 */

@HiltViewModel
class WorldChartViewModel @Inject constructor(
    loadWorldChartUseCase: LoadWorldChartUseCase
) : ViewModel() {
    val tracks = loadWorldChartUseCase(PAGE_SIZE).map {
        if (it is Result.Success) it.data
        else PagingData.empty()
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}