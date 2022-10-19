package com.example.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.datasource.paging.WorldChartPagingDataSource
import com.example.data.remote.ShazamApiService
import com.example.domain.model.Track
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 19/10/2022.
 */

interface Chart {
    fun loadWorldChart() : Flow<PagingData<Track>>
}

class ChartImpl @Inject constructor(
    private val shazamApiService: ShazamApiService
) : Chart {
    override fun loadWorldChart(): Flow<PagingData<Track>> {
        val worldChartPagingDataSource = WorldChartPagingDataSource(shazamApiService)
        return Pager(PagingConfig(pageSize = 20)) {
            worldChartPagingDataSource
        }.flow
    }
}