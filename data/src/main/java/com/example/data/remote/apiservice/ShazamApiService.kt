package com.example.data.remote.apiservice

import com.example.data.entity.*
import com.example.data.remote.*
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by TC on 14/10/2022.
 */
interface ShazamApiService {
    @GET(API_GET_WORLD_CHART)
    suspend fun getWorldChart(@Query("offset") offset: Int): List<TrackEntity>

    @GET(API_GET_WORLD_CHART_BY_GENRE)
    suspend fun getWorldChartByGenre(
        @Query("genre_code") genreCode: String?,
        @Query("offset") offset: Int
    ): List<TrackEntity>

    @GET(API_MULTI_SEARCH)
    suspend fun searchTracks(
        @Query("query") query: String?,
        @Query("search_type") searchType: String? = "SONGS",
        @Query("offset") offset: Int
    ): SearchTracksResult

    @GET(API_MULTI_SEARCH)
    suspend fun searchArtists(
        @Query("query") query: String?,
        @Query("search_type") searchType: String? = "ARTISTS",
        @Query("offset") offset: Int
    ): SearchArtistsResult

    @GET(API_GET_TRACK_DETAIL_V1)
    suspend fun getTrackDetailV1(@Query("track_id") trackId: String?): TrackEntity?

    @GET(API_GET_TRACK_DETAIL_V2)
    suspend fun getTrackDetailV2(@Query("track_id") trackId: String?): TrackV2Entity?

    @GET(API_GET_ARTIST_DETAIL)
    suspend fun getArtistDetail(@Query("artist_id") artistId: String?): ArtistEntity?
}
