package com.example.domain.usecase.chart

import androidx.paging.PagingData
import com.example.domain.FlowUseCase
import com.example.domain.Result
import com.example.domain.di.IoDispatcher
import com.example.domain.model.GenreCode
import com.example.domain.model.Track
import com.example.domain.repository.ChartRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by TC on 17/11/2022.
 */

class LoadWorldChartByGenreUseCase @Inject constructor(
    private val chartRepository: ChartRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<LoadWorldChartByGenreParams, PagingData<Track>>(dispatcher) {
    override fun execute(parameters: LoadWorldChartByGenreParams): Flow<Result<PagingData<Track>>> {
        return chartRepository.loadWorldChartByGenre(parameters.genreCode, parameters.offset).map {
            Result.Success(it)
        }
    }
}

data class LoadWorldChartByGenreParams(
    val genreCode: GenreCode,
    val offset: Int
)