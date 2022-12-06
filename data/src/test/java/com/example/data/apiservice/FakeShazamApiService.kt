package com.example.data.apiservice

import com.example.data.remote.apiservice.ShazamApiService
import com.example.model.Track
import com.example.test.data.*

/**
 * Created by TC on 03/11/2022.
 */
class FakeShazamApiService : ShazamApiService {
    val data = listOf(
        Track_1,
        Track_2,
        Track_3,
        Track_4,
        Track_5,
        Track_6,
        Track_7,
        Track_8,
        Track_9,
        Track_10,
    )

    override suspend fun getWorldChart(offset: Int): List<Track> {
        val pageSize = 3
        val startIndex = if (offset * pageSize > data.size - 1) data.size - 1 else offset * pageSize
        val endIndex = if (startIndex + 3 > data.size - 1) data.size - 1 else startIndex + 3

        return data.subList(startIndex, endIndex)
    }

    override suspend fun getWorldChartByGenre(genreCode: String?, offset: Int): List<Track> {
        val data = data.filter { it.genres?.primary == genreCode }

        val pageSize = 3
        val startIndex = if (offset * pageSize > data.size - 1) data.size - 1 else offset * pageSize
        val endIndex = if (startIndex + 3 > data.size - 1) data.size - 1 else startIndex + 3

        return data.subList(startIndex, endIndex)
    }

    override suspend fun getTrackDetail(trackId: String?): Track? {
        return data.find { it.key == trackId }
    }
}