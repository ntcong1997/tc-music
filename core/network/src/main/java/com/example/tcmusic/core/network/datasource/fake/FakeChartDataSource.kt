package com.example.tcmusic.core.network.datasource.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tcmusic.core.network.datasource.ChartDataSource
import com.example.tcmusic.core.network.datasource.paging.WorldChartByGenrePagingDataSource
import com.example.tcmusic.core.network.datasource.paging.WorldChartPagingDataSource
import com.example.tcmusic.core.network.model.NetworkTrackV1
import com.example.tcmusic.core.network.retrofit.fake.FakeRetrofitShazamNetwork
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 23/02/2023.
 */

class FakeChartDataSource @Inject constructor(
    private val fakeRetrofitShazamNetwork: FakeRetrofitShazamNetwork
) : ChartDataSource {
    private var worldChartPagingDataSource: WorldChartPagingDataSource? = null
    private var worldChartByGenrePagingDataSource: WorldChartByGenrePagingDataSource? = null

    override fun loadWorldChart(): Flow<PagingData<NetworkTrackV1>> {
        worldChartPagingDataSource = WorldChartPagingDataSource(fakeRetrofitShazamNetwork)
        return Pager(PagingConfig(pageSize = 20)) {
            worldChartPagingDataSource!!
        }.flow
    }

    override fun refreshWorldChart() {
        worldChartPagingDataSource?.invalidate()
        worldChartPagingDataSource = WorldChartPagingDataSource(fakeRetrofitShazamNetwork)
    }

    override fun loadWorldChartByGenre(genreCode: String?): Flow<PagingData<NetworkTrackV1>> {
        worldChartByGenrePagingDataSource = WorldChartByGenrePagingDataSource(genreCode, fakeRetrofitShazamNetwork)
        return Pager(PagingConfig(pageSize = 20)) {
            worldChartByGenrePagingDataSource!!
        }.flow
    }

    override fun refreshWorldChartByGenre(genreCode: String?) {
        worldChartByGenrePagingDataSource?.invalidate()
        worldChartByGenrePagingDataSource = WorldChartByGenrePagingDataSource(genreCode, fakeRetrofitShazamNetwork)
    }
}
