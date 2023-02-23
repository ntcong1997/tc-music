package com.example.tcmusic.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.tcmusic.core.data.model.toTrack
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.network.datasource.TrackDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by TC on 01/12/2022.
 */
interface TrackRepository {
    fun searchTracks(query: String?): Flow<PagingData<Track>>

    suspend fun getTrackDetail(trackId: String?, trackVersion: String?): Track?
}

class TrackRepositoryImpl @Inject constructor(
    private val trackDataSource: TrackDataSource
) : TrackRepository {
    override fun searchTracks(query: String?): Flow<PagingData<Track>> {
        return trackDataSource.searchTracks(query).map {
            it.map {
                it.toTrack()
            }
        }
    }

    override suspend fun getTrackDetail(trackId: String?, trackVersion: String?): Track? {
        return when(trackVersion) {
            "1" -> trackDataSource.getTrackDetailV1(trackId)?.toTrack()
            "2" -> trackDataSource.getTrackDetailV2(trackId)?.toTrack()
            else -> null
        }
    }
}
