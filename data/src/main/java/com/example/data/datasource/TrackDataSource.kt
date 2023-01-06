package com.example.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.datasource.paging.SearchTracksPagingDataSource
import com.example.data.remote.apiservice.ShazamApiService
import com.example.model.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by TC on 01/12/2022.
 */

interface TrackDataSource {
    fun searchTracks(query: String?): Flow<PagingData<Track>>

    suspend fun getTrackDetail(trackId: String?): Track?
}

class TrackDataSourceImpl @Inject constructor(
    private val shazamApiService: ShazamApiService
) : TrackDataSource {
    override fun searchTracks(query: String?): Flow<PagingData<Track>> {
        val searchTracksPagingDataSource = SearchTracksPagingDataSource(shazamApiService, query)
        return Pager(PagingConfig(pageSize = 20)) {
            searchTracksPagingDataSource
        }.flow
    }

    override suspend fun getTrackDetail(trackId: String?): Track? {
        return shazamApiService.getTrackDetail(trackId)
    }
}
