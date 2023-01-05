package com.example.domain.repository

import androidx.paging.PagingData
import com.example.model.Track
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 01/12/2022.
 */
interface TrackRepository {
    fun searchTracks(query: String?): Flow<PagingData<Track>>

    suspend fun getTrackDetail(trackId: String?): Track?
}
