package com.example.tcmusic.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.tcmusic.core.data.model.toArtist
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.network.datasource.ArtistDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by TC on 06/01/2023.
 */
interface ArtistRepository {
    fun searchArtists(query: String?): Flow<PagingData<Artist>>

    suspend fun getArtistDetail(artistId: String?): Artist?
}

class ArtistRepositoryImpl @Inject constructor(
    private val artistDataSource: ArtistDataSource
) : ArtistRepository {
    override fun searchArtists(query: String?): Flow<PagingData<Artist>> {
        return artistDataSource.searchArtists(query).map {
            it.map {
                it.toArtist()
            }
        }
    }

    override suspend fun getArtistDetail(artistId: String?): Artist? {
        return artistDataSource.getArtistDetail(artistId)?.toArtist()
    }
}
