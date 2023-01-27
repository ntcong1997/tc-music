package com.example.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.datasource.paging.SearchTracksPagingDataSource
import com.example.data.mapper.toTrack
import com.example.data.remote.apiservice.ShazamApiService
import com.example.model.Track
import com.google.gson.Gson
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 01/12/2022.
 */

interface TrackDataSource {
    fun searchTracks(query: String?): Flow<PagingData<Track>>

    suspend fun getTrackDetail(trackId: String?, trackVersion: String?): Track?
}

class TrackDataSourceImpl @Inject constructor(
    private val shazamApiService: ShazamApiService,
    private val gson: Gson
) : TrackDataSource {
    override fun searchTracks(query: String?): Flow<PagingData<Track>> {
        val searchTracksPagingDataSource = SearchTracksPagingDataSource(shazamApiService, query)
        return Pager(PagingConfig(pageSize = 20)) {
            searchTracksPagingDataSource
        }.flow
    }

    override suspend fun getTrackDetail(trackId: String?, trackVersion: String?): Track? {
        return when (trackVersion) {
            "1" -> shazamApiService.getTrackDetailV1(trackId)?.toTrack()
            "2" -> shazamApiService.getTrackDetailV2(trackId)?.toTrack(gson)
            else -> null
        }
    }
}
