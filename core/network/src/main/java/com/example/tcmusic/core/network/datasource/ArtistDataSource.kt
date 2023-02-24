package com.example.tcmusic.core.network.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tcmusic.core.network.datasource.paging.SearchArtistsPagingDataSource
import com.example.tcmusic.core.network.model.NetworkArtist
import com.example.tcmusic.core.network.retrofit.RetrofitShazamNetwork
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 06/01/2023.
 */

interface ArtistDataSource {
    fun searchArtists(query: String?): Flow<PagingData<NetworkArtist>>

    suspend fun getArtistDetail(artistId: String?): NetworkArtist?
}

class ArtistDataSourceImpl @Inject constructor(
    private val retrofitShazamNetwork: RetrofitShazamNetwork
) : ArtistDataSource {
    override fun searchArtists(query: String?): Flow<PagingData<NetworkArtist>> {
        val searchArtistsPagingDataSource = SearchArtistsPagingDataSource(retrofitShazamNetwork, query)
        return Pager(PagingConfig(pageSize = 20)) {
            searchArtistsPagingDataSource
        }.flow
    }

    override suspend fun getArtistDetail(artistId: String?): NetworkArtist? {
        return retrofitShazamNetwork.getArtistDetail(artistId)
    }
}
