package com.example.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.datasource.paging.SearchArtistsPagingDataSource
import com.example.data.remote.apiservice.ShazamApiService
import com.example.model.Artist
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 06/01/2023.
 */

interface ArtistDataSource {
    fun searchArtists(query: String?): Flow<PagingData<Artist>>

    suspend fun getArtistDetail(artistId: String?): Artist?
}

class ArtistDataSourceImpl @Inject constructor(
    private val shazamApiService: ShazamApiService
) : ArtistDataSource {
    override fun searchArtists(query: String?): Flow<PagingData<Artist>> {
        val searchArtistsPagingDataSource = SearchArtistsPagingDataSource(shazamApiService, query)
        return Pager(PagingConfig(pageSize = 20)) {
            searchArtistsPagingDataSource
        }.flow
    }

    override suspend fun getArtistDetail(artistId: String?): Artist? {
        return shazamApiService.getArtistDetail(artistId)
    }
}
