package com.example.data.datasource

import com.example.data.remote.apiservice.ShazamApiService
import com.example.model.Track
import javax.inject.Inject

/**
 * Created by TC on 01/12/2022.
 */

interface TrackDataSource {
    suspend fun getTrackDetail(trackId: String?): Track?
}

class TrackDataSourceImpl @Inject constructor(
    private val shazamApiService: ShazamApiService
) : TrackDataSource {
    override suspend fun getTrackDetail(trackId: String?): Track? {
        return shazamApiService.getTrackDetail(trackId)
    }
}
