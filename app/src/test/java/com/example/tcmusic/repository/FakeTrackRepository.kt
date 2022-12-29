package com.example.tcmusic.repository

import com.example.domain.repository.TrackRepository
import com.example.model.Track
import com.example.test.data.Tracks

/**
 * Created by TC on 29/12/2022.
 */

class FakeTrackRepository : TrackRepository {
    override suspend fun getTrackDetail(trackId: String?): Track? {
        return Tracks.find { it.key == trackId }
    }
}
