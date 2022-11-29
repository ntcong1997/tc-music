package com.example.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.datasource.paging.WorldChartByGenrePagingDataSource
import com.example.data.datasource.paging.WorldChartPagingDataSource
import com.example.data.remote.apiservice.ShazamApiService
import com.example.domain.model.GenreCode
import com.example.domain.model.Track
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 19/10/2022.
 */

interface ChartDataSource {
    fun loadWorldChart(pageSize: Int) : Flow<PagingData<Track>>

    fun loadWorldChartByGenre(genreCode: GenreCode, pageSize: Int) : Flow<PagingData<Track>>
}

class ChartDataSourceImpl @Inject constructor(
    private val shazamApiService: ShazamApiService
) : ChartDataSource {
    override fun loadWorldChart(pageSize: Int): Flow<PagingData<Track>> {
        val worldChartPagingDataSource = WorldChartPagingDataSource(shazamApiService)
        return Pager(PagingConfig(pageSize = pageSize)) {
            worldChartPagingDataSource
        }.flow
    }

    override fun loadWorldChartByGenre(genreCode: GenreCode, pageSize: Int): Flow<PagingData<Track>> {
        val worldChartByGenrePagingDataSource = WorldChartByGenrePagingDataSource(genreCode, shazamApiService)
        return Pager(PagingConfig(pageSize = pageSize)) {
            worldChartByGenrePagingDataSource
        }.flow
    }
}