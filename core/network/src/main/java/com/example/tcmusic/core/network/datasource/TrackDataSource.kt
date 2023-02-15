package com.example.tcmusic.core.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tcmusic.core.network.datasource.paging.SearchTracksPagingDataSource
import com.example.tcmusic.core.network.model.NetworkTrack
import com.example.tcmusic.core.network.model.NetworkTrackV2
import com.example.tcmusic.core.network.retrofit.RetrofitShazamNetwork
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 01/12/2022.
 */

interface TrackDataSource {
    fun searchTracks(query: String?): Flow<PagingData<NetworkTrack>>

    suspend fun getTrackDetailV1(trackId: String?): NetworkTrack?

    suspend fun getTrackDetailV2(trackId: String?): NetworkTrackV2?
}

class TrackDataSourceImpl @Inject constructor(
    private val retrofitShazamNetwork: RetrofitShazamNetwork
) : TrackDataSource {
    override fun searchTracks(query: String?): Flow<PagingData<NetworkTrack>> {
        val searchTracksPagingDataSource = SearchTracksPagingDataSource(retrofitShazamNetwork, query)
        return Pager(PagingConfig(pageSize = 20)) {
            searchTracksPagingDataSource
        }.flow
    }

    override suspend fun getTrackDetailV1(trackId: String?): NetworkTrack? {
        return retrofitShazamNetwork.getTrackDetailV1(trackId)
    }

    override suspend fun getTrackDetailV2(trackId: String?): NetworkTrackV2? {
        return retrofitShazamNetwork.getTrackDetailV2(trackId)
    }
}
