package com.example.domain.usecase.chart

import com.example.domain.CoroutineUseCase
import com.example.domain.di.IoDispatcher
import com.example.domain.repository.ChartRepository
import com.example.model.GenreCode
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by TC on 10/12/2022.
 */
class RefreshWorldChartByGenreUseCase @Inject constructor(
    private val chartRepository: ChartRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<GenreCode, Unit>(dispatcher) {
    override suspend fun execute(parameters: GenreCode) {
        chartRepository.refreshWorldChartByGenre(parameters)
    }
}