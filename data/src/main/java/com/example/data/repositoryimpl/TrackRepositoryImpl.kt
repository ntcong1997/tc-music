package com.example.data.repositoryimpl

import com.example.data.datasource.TrackDataSource
import com.example.domain.model.Track
import com.example.domain.repository.TrackRepository
import javax.inject.Inject

/**
 * Created by TC on 01/12/2022.
 */

class TrackRepositoryImpl @Inject constructor(
    private val trackDataSource: TrackDataSource
) : TrackRepository {
    override suspend fun getTrackDetail(trackId: String?): Track? {
        return trackDataSource.getTrackDetail(trackId)
    }
}