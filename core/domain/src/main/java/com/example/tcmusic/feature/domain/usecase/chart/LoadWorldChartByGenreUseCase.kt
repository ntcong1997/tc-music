package com.example.tcmusic.feature.domain.usecase.chart

import androidx.paging.PagingData
import com.example.tcmusic.feature.common.network.Dispatcher
import com.example.tcmusic.feature.common.network.TcMusicDispatchers
import com.example.tcmusic.feature.data.repository.ChartRepository
import com.example.tcmusic.feature.domain.usecase.FlowUseCase
import com.example.tcmusic.feature.model.Track
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 17/11/2022.
 */

class LoadWorldChartByGenreUseCase @Inject constructor(
    private val chartRepository: ChartRepository,
    @Dispatcher(TcMusicDispatchers.IO) dispatcher: CoroutineDispatcher
) : FlowUseCase<LoadWorldChartByGenreParams, PagingData<Track>>(dispatcher) {
    override fun execute(parameters: LoadWorldChartByGenreParams): Flow<PagingData<Track>> {
        return chartRepository.loadWorldChartByGenre(parameters.genreCode)
    }
}

data class LoadWorldChartByGenreParams(
    val genreCode: String?
)
