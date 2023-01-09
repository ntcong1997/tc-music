package com.example.tcmusic.repository

import androidx.paging.PagingData
import com.example.domain.repository.ArtistRepository
import com.example.model.Artist
import com.example.test.data.Artists
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by TC on 08/01/2023.
 */
class FakeArtistRepository : ArtistRepository {
    override fun searchArtists(query: String?): Flow<PagingData<Artist>> {
        return flow {
            emit(PagingData.from(Artists.filter { it.artistName?.contains(query ?: "", ignoreCase = true) == true }))
        }
    }
}
