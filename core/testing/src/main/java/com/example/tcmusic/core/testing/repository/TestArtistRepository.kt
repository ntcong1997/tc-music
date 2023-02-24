package com.example.tcmusic.core.testing.repository

import androidx.paging.PagingData
import com.example.tcmusic.core.data.repository.ArtistRepository
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.testing.data.artistsTestData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by TC on 08/01/2023.
 */
class TestArtistRepository : ArtistRepository {
    override fun searchArtists(query: String?): Flow<PagingData<Artist>> {
        return flow {
            emit(PagingData.from(artistsTestData.filter { it.name?.contains(query.orEmpty(), ignoreCase = true) == true }))
        }
    }

    override suspend fun getArtistDetail(artistId: String?): Artist? {
        return artistsTestData.find { it.id == artistId }
    }
}
