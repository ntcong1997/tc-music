package com.example.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.datasource.paging.WorldChartByGenrePagingDataSource
import com.example.data.datasource.paging.WorldChartPagingDataSource
import com.example.data.remote.apiservice.ShazamApiService
import com.example.model.GenreCode
import com.example.model.Track
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 19/10/2022.
 */

interface ChartDataSource {
    fun loadWorldChart(pageSize: Int): Flow<PagingData<Track>>

    fun refreshWorldChart()

    fun loadWorldChartByGenre(genreCode: GenreCode, pageSize: Int): Flow<PagingData<Track>>

    fun refreshWorldChartByGenre(genreCode: GenreCode)
}

class ChartDataSourceImpl @Inject constructor(
    private val shazamApiService: ShazamApiService
) : ChartDataSource {
    private var worldChartPagingDataSource: WorldChartPagingDataSource? = null
    private var worldChartByGenrePagingDataSource: WorldChartByGenrePagingDataSource? = null

    override fun loadWorldChart(pageSize: Int): Flow<PagingData<Track>> {
        worldChartPagingDataSource = WorldChartPagingDataSource(shazamApiService)
        return Pager(PagingConfig(pageSize = pageSize)) {
            worldChartPagingDataSource!!
        }.flow
    }

    override fun refreshWorldChart() {
        worldChartPagingDataSource?.invalidate()
        worldChartPagingDataSource = WorldChartPagingDataSource(shazamApiService)
    }

    override fun loadWorldChartByGenre(genreCode: GenreCode, pageSize: Int): Flow<PagingData<Track>> {
        worldChartByGenrePagingDataSource = WorldChartByGenrePagingDataSource(genreCode, shazamApiService)
        return Pager(PagingConfig(pageSize = pageSize)) {
            worldChartByGenrePagingDataSource!!
        }.flow
    }

    override fun refreshWorldChartByGenre(genreCode: GenreCode) {
        worldChartByGenrePagingDataSource?.invalidate()
        worldChartByGenrePagingDataSource = WorldChartByGenrePagingDataSource(genreCode, shazamApiService)
    }
}
