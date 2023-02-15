package com.example.tcmusic.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.tcmusic.core.data.model.toTrack
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.network.datasource.ChartDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by TC on 17/11/2022.
 */
interface ChartRepository {
    fun loadWorldChart(): Flow<PagingData<Track>>

    fun refreshWorldChart()

    fun loadWorldChartByGenre(genreCode: String?): Flow<PagingData<Track>>

    fun refreshWorldChartByGenre(genreCode: String?)
}

class ChartRepositoryImpl @Inject constructor(
    private val chartDataSource: ChartDataSource
) : ChartRepository {
    override fun loadWorldChart(): Flow<PagingData<Track>> {
        return chartDataSource.loadWorldChart().map {
            it.map {
                it.toTrack()
            }
        }
    }

    override fun refreshWorldChart() {
        return chartDataSource.refreshWorldChart()
    }

    override fun loadWorldChartByGenre(genreCode: String?): Flow<PagingData<Track>> {
        return chartDataSource.loadWorldChartByGenre(genreCode).map {
            it.map {
                it.toTrack()
            }
        }
    }

    override fun refreshWorldChartByGenre(genreCode: String?) {
        return chartDataSource.refreshWorldChartByGenre(genreCode)
    }
}
