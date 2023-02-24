package com.example.tcmusic.core.network.retrofit.fake

import JvmUnitTestFakeAssetManager
import com.example.tcmusic.core.common.network.Dispatcher
import com.example.tcmusic.core.common.network.TcMusicDispatchers
import com.example.tcmusic.core.network.model.*
import com.example.tcmusic.core.network.retrofit.RetrofitShazamNetwork
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

/**
 * Created by TC on 22/02/2023.
 */

class FakeRetrofitShazamNetwork @Inject constructor(
    @Dispatcher(TcMusicDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : RetrofitShazamNetwork {

    companion object {
        private const val CHART_ASSET = "chart.json"
        private const val SEARCH_TRACKS_ASSET = "searchTracks.json"
        private const val SEARCH_ARTISTS_ASSET = "searchArtists.json"
        private const val ARTIST_ASSET = "artist.json"
        private const val TRACK_V1_ASSET = "trackV1.json"
        private const val TRACK_V2_ASSET = "trackV2.json"
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getWorldChart(offset: Int): List<NetworkTrackV1> = withContext(ioDispatcher) {
        assets.open(CHART_ASSET).use(networkJson::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getWorldChartByGenre(
        genreCode: String?,
        offset: Int
    ): List<NetworkTrackV1> = withContext(ioDispatcher) {
        assets.open(CHART_ASSET).use(networkJson::decodeFromStream)
    }

    // query search: star
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun searchTracks(
        query: String?,
        searchType: String?,
        offset: Int
    ): NetworkSearchTracks = withContext(ioDispatcher) {
        assets.open(SEARCH_TRACKS_ASSET).use(networkJson::decodeFromStream)
    }

    // query search: star
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun searchArtists(
        query: String?,
        searchType: String?,
        offset: Int
    ): NetworkSearchArtists = withContext(ioDispatcher) {
        assets.open(SEARCH_ARTISTS_ASSET).use(networkJson::decodeFromStream)
    }

    // trackId: 648859694
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTrackDetailV1(trackId: String?): NetworkTrackV1? = withContext(ioDispatcher) {
        assets.open(TRACK_V1_ASSET).use(networkJson::decodeFromStream)
    }

    // trackId: 1663973562
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getTrackDetailV2(trackId: String?): NetworkTrackV2? = withContext(ioDispatcher) {
        assets.open(TRACK_V2_ASSET).use(networkJson::decodeFromStream)
    }

    // artistId: 137057909
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getArtistDetail(artistId: String?): NetworkArtist? = withContext(ioDispatcher) {
        assets.open(ARTIST_ASSET).use(networkJson::decodeFromStream)
    }
}
