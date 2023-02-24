package com.example.tcmusic.core.testing.repository

import androidx.paging.PagingData
import com.example.tcmusic.core.data.repository.TrackRepository
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.testing.data.tracksTestData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by TC on 29/12/2022.
 */

class TestTrackRepository : TrackRepository {
    override fun searchTracks(query: String?): Flow<PagingData<Track>> {
        return flow {
            emit(PagingData.from(tracksTestData.filter { it.title?.contains(query.orEmpty(), ignoreCase = true) == true }))
        }
    }

    override suspend fun getTrackDetail(trackId: String?, trackVersion: String?): Track? {
        return tracksTestData.find { it.id == trackId }
    }
}
