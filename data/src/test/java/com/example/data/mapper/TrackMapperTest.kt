package com.example.data.mapper

import com.example.data.entity.*
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by TC on 28/01/2023.
 */

class TrackMapperTest {
    private val gson = Gson()

    @Test
    fun `track v2 entity map to track`() {
        val trackV2Entity = TrackV2Entity(
            resources = Resources(
                albums = null,
                artists = null,
                lyrics = mapOf(
                    "123" to Lyric(
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
                    "123" to ShazamSong(
                        attributes = ShazamSongAttributes(
                            artist = "One Direction",
                            explicit = null,
                            genres = Genres(
                                primary = "Pop"
                            ),
                            images = ShazamSongAttributesImages(
                                artistAvatar = null,
                                coverArt = "https://image...",
                                coverArtHq = null
                            ),
                            isrc = null,
                            label = null,
                            primaryArtist = null,
                            share = null,
                            streaming = Streaming(
                                deeplink = null,
                                preview = "https://uri...",
                                store = null
                            ),
                            title = "Story of my life",
                            type = null,
                            webUrl = null
                        ),
                        id = "1",
                        type = null
                    )
                )
            )
        )
        val track = trackV2Entity.toTrack(gson)

        assertEquals("1", track.id)
        assertEquals("https://image...", track.image)
        assertEquals("Pop", track.genre)
        assertEquals(listOf("A", "B", "C"), track.lyrics)
        assertEquals("Story of my life", track.title)
        assertEquals("One Direction", track.subTitle)
        assertEquals("https://uri...", track.uri)
    }
}
