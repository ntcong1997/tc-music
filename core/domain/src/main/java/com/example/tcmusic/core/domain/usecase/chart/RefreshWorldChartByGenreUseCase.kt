package com.example.tcmusic.core.domain.usecase.chart

import com.example.tcmusic.core.data.repository.ChartRepository
import javax.inject.Inject

/**
 * Created by TC on 10/12/2022.
 */
class RefreshWorldChartByGenreUseCase @Inject constructor(
    private val chartRepository: ChartRepository
) {
    operator fun invoke(parameters: String?) {
        chartRepository.refreshWorldChartByGenre(parameters)
    }
}
