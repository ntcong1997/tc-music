package com.example.tcmusic.core.network.datasource.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tcmusic.core.network.datasource.ArtistDataSource
import com.example.tcmusic.core.network.datasource.paging.SearchArtistsPagingDataSource
import com.example.tcmusic.core.network.model.NetworkArtist
import com.example.tcmusic.core.network.retrofit.fake.FakeRetrofitShazamNetwork
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 23/02/2023.
 */

class FakeArtistDataSource @Inject constructor(
    private val fakeRetrofitShazamNetwork: FakeRetrofitShazamNetwork
) : ArtistDataSource {
    override fun searchArtists(query: String?): Flow<PagingData<NetworkArtist>> {
        val searchArtistsPagingDataSource = SearchArtistsPagingDataSource(fakeRetrofitShazamNetwork, query)
        return Pager(PagingConfig(pageSize = 20)) {
            searchArtistsPagingDataSource
        }.flow
    }

    override suspend fun getArtistDetail(artistId: String?): NetworkArtist? {
        return fakeRetrofitShazamNetwork.getArtistDetail(artistId)
    }
}
