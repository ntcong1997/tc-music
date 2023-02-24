package com.example.tcmusic.core.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tcmusic.core.network.datasource.paging.WorldChartByGenrePagingDataSource
import com.example.tcmusic.core.network.datasource.paging.WorldChartPagingDataSource
import com.example.tcmusic.core.network.model.NetworkTrackV1
import com.example.tcmusic.core.network.retrofit.RetrofitShazamNetwork
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 19/10/2022.
 */

interface ChartDataSource {
    fun loadWorldChart(): Flow<PagingData<NetworkTrackV1>>

    fun refreshWorldChart()

    fun loadWorldChartByGenre(genreCode: String?): Flow<PagingData<NetworkTrackV1>>

    fun refreshWorldChartByGenre(genreCode: String?)
}

class ChartDataSourceImpl @Inject constructor(
    private val retrofitShazamNetwork: RetrofitShazamNetwork
) : ChartDataSource {
    private var worldChartPagingDataSource: WorldChartPagingDataSource? = null
    private var worldChartByGenrePagingDataSource: WorldChartByGenrePagingDataSource? = null

    override fun loadWorldChart(): Flow<PagingData<NetworkTrackV1>> {
        worldChartPagingDataSource = WorldChartPagingDataSource(retrofitShazamNetwork)
        return Pager(PagingConfig(pageSize = 20)) {
            worldChartPagingDataSource!!
        }.flow
    }

    override fun refreshWorldChart() {
        worldChartPagingDataSource?.invalidate()
        worldChartPagingDataSource = WorldChartPagingDataSource(retrofitShazamNetwork)
    }

    override fun loadWorldChartByGenre(genreCode: String?): Flow<PagingData<NetworkTrackV1>> {
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
