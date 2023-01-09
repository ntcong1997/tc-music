package com.example.domain.repository

import androidx.paging.PagingData
import com.example.model.GenreCode
import com.example.model.Track
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 17/11/2022.
 */
interface ChartRepository {
    fun loadWorldChart(): Flow<PagingData<Track>>

    fun refreshWorldChart()

    fun loadWorldChartByGenre(genreCode: GenreCode): Flow<PagingData<Track>>

    fun refreshWorldChartByGenre(genreCode: GenreCode)
}
