package com.example.domain.repository

import androidx.paging.PagingData
import com.example.model.Artist
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 06/01/2023.
 */
interface ArtistRepository {
    fun searchArtists(query: String?): Flow<PagingData<Artist>>
}
