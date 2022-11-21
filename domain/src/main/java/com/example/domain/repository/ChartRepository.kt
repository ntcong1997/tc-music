package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Track
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 17/11/2022.
 */
interface ChartRepository {
    fun loadWorldChart(pageSize: Int) : Flow<PagingData<Track>>
}