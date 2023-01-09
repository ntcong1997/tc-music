package com.example.data.repositoryimpl

import androidx.paging.PagingData
import com.example.data.datasource.ArtistDataSource
import com.example.domain.repository.ArtistRepository
import com.example.model.Artist
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 06/01/2023.
 */

class ArtistRepositoryImpl @Inject constructor(
    private val artistDataSource: ArtistDataSource
) : ArtistRepository {
    override fun searchArtists(query: String?): Flow<PagingData<Artist>> {
        return artistDataSource.searchArtists(query)
    }

    override suspend fun getArtistDetail(artistId: String?): Artist? {
        return artistDataSource.getArtistDetail(artistId)
    }
}
