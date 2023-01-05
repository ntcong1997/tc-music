package com.example.data.repositoryimpl

import androidx.paging.PagingData
import com.example.data.datasource.TrackDataSource
import com.example.domain.repository.TrackRepository
import com.example.model.Track
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 01/12/2022.
 */

class TrackRepositoryImpl @Inject constructor(
    private val trackDataSource: TrackDataSource
) : TrackRepository {
    override fun searchTracks(query: String?): Flow<PagingData<Track>> {
        return trackDataSource.searchTracks(query)
    }

    override suspend fun getTrackDetail(trackId: String?): Track? {
        return trackDataSource.getTrackDetail(trackId)
    }
}
