package com.example.tcmusic.core.network.retrofit

import com.example.tcmusic.core.network.model.*
import com.example.tcmusic.core.network.model.*
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by TC on 15/02/2023.
 */
interface RetrofitShazamNetwork {
    @GET(value = "v1/charts/world")
    suspend fun getWorldChart(@Query("offset") offset: Int): List<NetworkTrack>

    @GET(value = "v1/charts/genre-world")
    suspend fun getWorldChartByGenre(
        @Query("genre_code") genreCode: String?,
        @Query("offset") offset: Int
    ): List<NetworkTrack>

    @GET(value = "v1/search/multi")
    suspend fun searchTracks(
        @Query("query") query: String?,
        @Query("search_type") searchType: String? = "SONGS",
        @Query("offset") offset: Int
    ): NetworkSearchTracks

    @GET(value = "v1/search/multi")
    suspend fun searchArtists(
        @Query("query") query: String?,
        @Query("search_type") searchType: String? = "ARTISTS",
        @Query("offset") offset: Int
    ): NetworkSearchArtists

    @GET(value = "v1/tracks/details")
    suspend fun getTrackDetailV1(@Query("track_id") trackId: String?): NetworkTrack?

    @GET(value = "v2/tracks/details")
    suspend fun getTrackDetailV2(@Query("track_id") trackId: String?): NetworkTrackV2?

    @GET(value = "v2/artists/details")
    suspend fun getArtistDetail(@Query("artist_id") artistId: String?): NetworkArtist?
}