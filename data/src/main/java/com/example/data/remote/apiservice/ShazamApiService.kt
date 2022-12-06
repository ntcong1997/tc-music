package com.example.data.remote.apiservice

import com.example.data.remote.API_GET_TRACK_DETAIL
import com.example.data.remote.API_GET_WORLD_CHART
import com.example.data.remote.API_GET_WORLD_CHART_BY_GENRE
import com.example.model.Track
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by TC on 14/10/2022.
 */
interface ShazamApiService {
    @GET(API_GET_WORLD_CHART)
    suspend fun getWorldChart(@Query("offset") offset: Int): List<Track>

    @GET(API_GET_WORLD_CHART_BY_GENRE)
    suspend fun getWorldChartByGenre(
        @Query("genre_code") genreCode: String?,
        @Query("offset") offset: Int
    ): List<Track>

    @GET(API_GET_TRACK_DETAIL)
    suspend fun getTrackDetail(@Query("track_id") trackId: String?): Track?
}
