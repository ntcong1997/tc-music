package com.example.data.repositoryimpl

import androidx.paging.PagingData
import com.example.data.datasource.ChartDataSource
import com.example.model.GenreCode
import com.example.model.Track
import com.example.domain.repository.ChartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 17/11/2022.
 */

class ChartRepositoryImpl @Inject constructor(
    private val chartDataSource: ChartDataSource
) : ChartRepository {
    override fun loadWorldChart(pageSize: Int): Flow<PagingData<Track>> {
        return chartDataSource.loadWorldChart(pageSize)
    }

    override fun loadWorldChartByGenre(genreCode: GenreCode, pageSize: Int) : Flow<PagingData<Track>> {
        return chartDataSource.loadWorldChartByGenre(genreCode, pageSize)
    }
}