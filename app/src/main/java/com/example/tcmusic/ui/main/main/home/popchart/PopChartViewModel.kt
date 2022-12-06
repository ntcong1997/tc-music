package com.example.tcmusic.ui.main.main.home.popchart

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.domain.Result
import com.example.domain.usecase.chart.LoadWorldChartByGenreParams
import com.example.domain.usecase.chart.LoadWorldChartByGenreUseCase
import com.example.model.GenreCode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.map

/**
 * Created by TC on 22/11/2022.
 */

@HiltViewModel
class PopChartViewModel @Inject constructor(
    loadWorldChartByGenreUseCase: LoadWorldChartByGenreUseCase
) : ViewModel() {
    val tracks = loadWorldChartByGenreUseCase(LoadWorldChartByGenreParams(GenreCode.POP, PAGE_SIZE)).map {
            if (it is Result.Success) it.data
            else PagingData.empty()
        }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
