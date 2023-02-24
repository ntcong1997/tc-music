package com.example.tcmusic.core.domain.usecase.chart

import androidx.paging.PagingData
import com.example.tcmusic.core.common.network.Dispatcher
import com.example.tcmusic.core.common.network.TcMusicDispatchers
import com.example.tcmusic.core.data.repository.ChartRepository
import com.example.tcmusic.core.domain.usecase.FlowUseCase
import com.example.tcmusic.core.model.Track
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 17/11/2022.
 */

class LoadWorldChartUseCase @Inject constructor(
    private val chartRepository: ChartRepository,
    @Dispatcher(TcMusicDispatchers.IO) dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, PagingData<Track>>(dispatcher) {
    override fun execute(parameters: Unit): Flow<PagingData<Track>> {
        return chartRepository.loadWorldChart()
    }
}
