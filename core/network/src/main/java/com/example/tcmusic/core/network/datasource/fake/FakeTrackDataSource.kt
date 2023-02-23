package com.example.tcmusic.core.network.datasource.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tcmusic.core.network.datasource.TrackDataSource
import com.example.tcmusic.core.network.datasource.paging.SearchTracksPagingDataSource
import com.example.tcmusic.core.network.model.NetworkTrackV1
import com.example.tcmusic.core.network.model.NetworkTrackV2
import com.example.tcmusic.core.network.retrofit.fake.FakeRetrofitShazamNetwork
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 23/02/2023.
 */

class FakeTrackDataSource @Inject constructor(
    private val fakeRetrofitShazamNetwork: FakeRetrofitShazamNetwork
) : TrackDataSource {
    override fun searchTracks(query: String?): Flow<PagingData<NetworkTrackV1>> {
        val searchTracksPagingDataSource = SearchTracksPagingDataSource(fakeRetrofitShazamNetwork, query)
        return Pager(PagingConfig(pageSize = 20)) {
            searchTracksPagingDataSource
        }.flow
    }

    override suspend fun getTrackDetailV1(trackId: String?): NetworkTrackV1? {
        return fakeRetrofitShazamNetwork.getTrackDetailV1(trackId)
    }

    override suspend fun getTrackDetailV2(trackId: String?): NetworkTrackV2? {
        return fakeRetrofitShazamNetwork.getTrackDetailV2(trackId)
    }
}