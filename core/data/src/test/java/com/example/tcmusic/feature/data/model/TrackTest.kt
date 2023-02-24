package com.example.tcmusic.feature.data.model

import com.example.tcmusic.core.data.model.toTrack
import com.example.tcmusic.core.network.model.*
import kotlin.test.assertEquals
import org.junit.Test

/**
 * Created by TC on 22/02/2023.
 */
class TrackTest {
    @Test
    fun `NetworkTrackV1 map to Track`() {
        val networkTrackV1 = NetworkTrackV1(
            alias = null,
            artists = listOf(),
            genres = Genres(primary = "genre"),
            hub = Hub(
                actions = listOf(
                    Action(
                        id = null,
                        name = null,
                        type = "uri",
                        uri = "uri"
                    )
                ),
                displayname = null,
                explicit = null,
                image = null,
                options = null,
                type = null
            ),
            images = NetworkTrackV1Images(
                background = null,
                coverart = "image",
                coverarthq = null,
                joecolor = null
            ),
            key = "1",
            layout = null,
            releasedate = null,
            sections = listOf(
                Section(
                    beacondata = null,
                    footer = null,
                    metadata = null,
                    metapages = null,
                    tabname = null,
                    text = listOf("A", "B", "C"),
                    type = "LYRICS"
                )
            ),
            share = null,
            subtitle = "subTitle",
            title = "title",
            type = null,
            url = null,
            urlparams = null
        )

        val track = networkTrackV1.toTrack()

        assertEquals("1", track.id)
        assertEquals("image", track.image)
        assertEquals("genre", track.genre)
        assertEquals(listOf("A", "B", "C"), track.lyrics)
        assertEquals("title", track.title)
        assertEquals("subTitle", track.subTitle)
        assertEquals("uri", track.uri)
        assertEquals("1", track.version)
    }

    @Test
    fun `NetworkTrackV2 map to Track`() {
        val networkTrackV2 = NetworkTrackV2(
            resources = Resources(
                albums = null,
                artists = null,
                lyrics = mapOf(
                    "lyric" to Lyric(
                        attributes = LyricAttributes(
                            footer = null,
                            musixmatchLyricsId = null,
                            providerName = null,
                            syncAvailable = null,
                            text = listOf("A", "B", "C")
                        ),
                        href = null,
                        id = null,
                        type = null
                    )
                ),
                relatedTracks = null,
                shazamSongs = mapOf(
                    "shazamSong" to ShazamSong(
                        attributes = ShazamSongAttributes(
                            artist = "subTitle",
                            explicit = null,
                            genres = Genres(primary = "genre"),
                            images = ShazamSongAttributesImages(
                                artistAvatar = null,
                                coverArt = "image",
                                coverArtHq = null
                            ),
                            isrc = null,
                            label = null,
                            primaryArtist = null,
                            share = null,
                            streaming = Streaming(
                                deeplink = null,
                                preview = "uri",
                                store = null
                            ),
                            title = "title",
                            type = null,
                            webUrl = null
                        ),
                        id = "1",
                        type = null
                    )
                )
            )
        )

        val track = networkTrackV2.toTrack()

        assertEquals("1", track.id)
        assertEquals("image", track.image)
        assertEquals("genre", track.genre)
        assertEquals(listOf("A", "B", "C"), track.lyrics)
        assertEquals("title", track.title)
        assertEquals("subTitle", track.subTitle)
        assertEquals("uri", track.uri)
        assertEquals("2", track.version)
    }
}
