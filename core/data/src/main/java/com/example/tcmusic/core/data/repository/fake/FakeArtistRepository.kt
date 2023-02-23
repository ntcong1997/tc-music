package com.example.tcmusic.core.data.repository.fake

import androidx.paging.PagingData
import androidx.paging.map
import com.example.tcmusic.core.data.model.toArtist
import com.example.tcmusic.core.data.repository.ArtistRepository
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.network.datasource.ArtistDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by TC on 23/02/2023.
 */

class FakeArtistRepository @Inject constructor(
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