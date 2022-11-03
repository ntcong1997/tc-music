package com.example.data.apiservice

import com.example.data.remote.apiservice.ShazamApiService
import com.example.domain.data.Track_1
import com.example.domain.data.Track_2
import com.example.domain.data.Track_3
import com.example.domain.model.Track

/**
 * Created by TC on 03/11/2022.
 */
class FakeShazamApiService : ShazamApiService {
    override suspend fun getWorldChart(offset: Int): List<Track> {
        return listOf(
            Track_1,
            Track_2,
            Track_3
        )
    }
}