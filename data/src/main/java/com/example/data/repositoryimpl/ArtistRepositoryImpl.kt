package com.example.data.repositoryimpl

import androidx.paging.PagingData
import com.example.data.datasource.ArtistDataSource
import com.example.domain.repository.ArtistRepository
import com.example.model.Artist
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 06/01/2023.
 */

class ArtistRepositoryImpl @Inject constructor(
    private val artistDataSource: ArtistDataSource
) : ArtistRepository {
    override fun searchArtists(query: String?): Flow<PagingData<Artist>> {
        return artistDataSource.searchArtists(query)
    }
}