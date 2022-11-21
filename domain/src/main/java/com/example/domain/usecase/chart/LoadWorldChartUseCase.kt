package com.example.domain.usecase.chart

import androidx.paging.PagingData
import com.example.domain.FlowUseCase
import com.example.domain.Result
import com.example.domain.di.IoDispatcher
import com.example.domain.model.Track
import com.example.domain.repository.ChartRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by TC on 17/11/2022.
 */

class LoadWorldChartUseCase @Inject constructor(
    private val chartRepository: ChartRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Int, PagingData<Track>>(dispatcher) {
    override fun execute(parameters: Int): Flow<Result<PagingData<Track>>> {
        return chartRepository.loadWorldChart(parameters).map {
            Result.Success(it)
        }
    }
}