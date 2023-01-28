package com.example.tcmusic.repository

import androidx.paging.PagingData
import com.example.domain.repository.TrackRepository
import com.example.model.Track
import com.example.test.data.Tracks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by TC on 29/12/2022.
 */

class FakeTrackRepository : TrackRepository {
    override fun searchTracks(query: String?): Flow<PagingData<Track>> {
        return flow {
            emit(PagingData.from(Tracks.filter { it.title?.contains(query ?: "", ignoreCase = true) == true }))
        }
    }

    override suspend fun getTrackDetail(trackId: String?, trackVersion: String?): Track? {
        return Tracks.find { it.id == trackId }
    }
}
