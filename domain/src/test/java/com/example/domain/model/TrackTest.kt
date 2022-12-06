package com.example.domain.model

import com.example.model.*
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by TC on 04/12/2022.
 */

class TrackTest {
    @Test
    fun track_has_lyrics() {
        val track = Track(
            alias = null,
            artists = listOf(),
            genres = Genres("POP"),
            hub = Hub(
                actions = listOf(
                    Action(
                        id = "123",
                        name = "apple",
                        type = null,
                        uri = "https://..."
                    )
                ),
                displayname = null,
                explicit = null,
                image = null,
                options = null,
                type = null
            ),
            images = Images(
                background = null,
                coverart = null,
                coverarthq = null
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
            subtitle = "One Direction",
            title = "Story of my life",
            type = "MUSIC",
            url = "https://...",
            urlparams = null
        )

        val expectedResult = listOf("A", "B", "C")
        val actualResult = track.lyrics

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun track_has_no_lyrics_sections_is_null() {
        val track = Track(
            alias = null,
            artists = listOf(),
            genres = Genres("POP"),
            hub = Hub(
                actions = listOf(
                    Action(
                        id = "123",
                        name = "apple",
                        type = null,
                        uri = "https://..."
                    )
                ),
                displayname = null,
                explicit = null,
                image = null,
                options = null,
                type = null
            ),
            images = Images(
                background = null,
                coverart = null,
                coverarthq = null
            ),
            key = "1",
            layout = null,
            releasedate = null,
            sections = null,
            share = null,
            subtitle = "One Direction",
            title = "Story of my life",
            type = "MUSIC",
            url = "https://...",
            urlparams = null
        )

        val expectedResult = listOf<String>()
        val actualResult = track.lyrics

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun track_has_no_lyrics_sections_have_no_element_lyrics() {
        val track = Track(
            alias = null,
            artists = listOf(),
            genres = Genres("POP"),
            hub = Hub(
                actions = listOf(
                    Action(
                        id = "123",
                        name = "apple",
                        type = null,
                        uri = "https://..."
                    )
                ),
                displayname = null,
                explicit = null,
                image = null,
                options = null,
                type = null
            ),
            images = Images(
                background = null,
                coverart = null,
                coverarthq = null
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
                    text = null,
                    type = "SONG"
                )
            ),
            share = null,
            subtitle = "One Direction",
            title = "Story of my life",
            type = "MUSIC",
            url = "https://...",
            urlparams = null
        )

        val expectedResult = listOf<String>()
        val actualResult = track.lyrics

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun track_has_media_uri() {
        val track = Track(
            alias = null,
            artists = listOf(),
            genres = Genres("POP"),
            hub = Hub(
                actions = listOf(
                    Action(
                        id = null,
                        name = "apple",
                        type = "uri",
                        uri = "https://..."
                    )
                ),
                displayname = null,
                explicit = null,
                image = null,
                options = null,
                type = null
            ),
            images = Images(
                background = null,
                coverart = null,
                coverarthq = null
            ),
            key = "1",
            layout = null,
            releasedate = null,
            sections = null,
            share = null,
            subtitle = "One Direction",
            title = "Story of my life",
            type = "MUSIC",
            url = "https://...",
            urlparams = null
        )

        val expectedResult = "https://..."
        val actualResult = track.mediaUri

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun track_has_no_media_uri_hub_is_null() {
        val track = Track(
            alias = null,
            artists = listOf(),
            genres = Genres("POP"),
            hub = null,
            images = Images(
                background = null,
                coverart = null,
                coverarthq = null
            ),
            key = "1",
            layout = null,
            releasedate = null,
            sections = null,
            share = null,
            subtitle = "One Direction",
            title = "Story of my life",
            type = "MUSIC",
            url = "https://...",
            urlparams = null
        )

        val expectedResult = ""
        val actualResult = track.mediaUri

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun track_has_no_media_uri_hub_has_no_element_uri() {
        val track = Track(
            alias = null,
            artists = listOf(),
            genres = Genres("POP"),
            hub = Hub(
                actions = listOf(
                    Action(
                        id = "123",
                        name = "apple",
                        type = "applemusicplay",
                        uri = null
                    )
                ),
                displayname = null,
                explicit = null,
                image = null,
                options = null,
                type = null
            ),
            images = Images(
                background = null,
                coverart = null,
                coverarthq = null
            ),
            key = "1",
            layout = null,
            releasedate = null,
            sections = null,
            share = null,
            subtitle = "One Direction",
            title = "Story of my life",
            type = "MUSIC",
            url = "https://...",
            urlparams = null
        )

        val expectedResult = ""
        val actualResult = track.mediaUri

        assertEquals(expectedResult, actualResult)
    }
}