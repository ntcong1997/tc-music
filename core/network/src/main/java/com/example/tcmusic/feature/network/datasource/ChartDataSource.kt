package com.example.tcmusic.feature.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tcmusic.feature.network.datasource.paging.WorldChartByGenrePagingDataSource
import com.example.tcmusic.feature.network.datasource.paging.WorldChartPagingDataSource
import com.example.tcmusic.feature.network.model.NetworkTrack
import com.example.tcmusic.feature.network.retrofit.RetrofitShazamNetwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 19/10/2022.
 */

interface ChartDataSource {
    fun loadWorldChart(): Flow<PagingData<NetworkTrack>>

    fun refreshWorldChart()

    fun loadWorldChartByGenre(genreCode: String?): Flow<PagingData<NetworkTrack>>

    fun refreshWorldChartByGenre(genreCode: String?)
}

class ChartDataSourceImpl @Inject constructor(
    private val retrofitShazamNetwork: RetrofitShazamNetwork
) : ChartDataSource {
    private var worldChartPagingDataSource: WorldChartPagingDataSource? = null
    private var worldChartByGenrePagingDataSource: WorldChartByGenrePagingDataSource? = null

    override fun loadWorldChart(): Flow<PagingData<NetworkTrack>> {
        worldChartPagingDataSource = WorldChartPagingDataSource(retrofitShazamNetwork)
        return Pager(PagingConfig(pageSize = 20)) {
            worldChartPagingDataSource!!
        }.flow
    }

    override fun refreshWorldChart() {
        worldChartPagingDataSource?.invalidate()
        worldChartPagingDataSource = WorldChartPagingDataSource(retrofitShazamNetwork)
    }

    override fun loadWorldChartByGenre(genreCode: String?): Flow<PagingData<NetworkTrack>> {
        worldChartByGenrePagingDataSource = WorldChartByGenrePagingDataSource(genreCode, retrofitShazamNetwork)
        return Pager(PagingConfig(pageSize = 20)) {
            worldChartByGenrePagingDataSource!!
        }.flow
    }

    override fun refreshWorldChartByGenre(genreCode: String?) {
        worldChartByGenrePagingDataSource?.invalidate()
        worldChartByGenrePagingDataSource = WorldChartByGenrePagingDataSource(genreCode, retrofitShazamNetwork)
    }
}
