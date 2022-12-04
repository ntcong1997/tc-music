package com.example.domain.repository

import com.example.domain.model.Track

/**
 * Created by TC on 01/12/2022.
 */
interface TrackRepository {
    suspend fun getTrackDetail(trackId: String?) : Track?
}