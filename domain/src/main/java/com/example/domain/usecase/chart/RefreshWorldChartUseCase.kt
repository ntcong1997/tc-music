package com.example.domain.usecase.chart

import com.example.domain.CoroutineUseCase
import com.example.domain.di.IoDispatcher
import com.example.domain.repository.ChartRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by TC on 10/12/2022.
 */
class RefreshWorldChartUseCase @Inject constructor(
    private val chartRepository: ChartRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(parameters: Unit) {
        chartRepository.refreshWorldChart()
    }
}
