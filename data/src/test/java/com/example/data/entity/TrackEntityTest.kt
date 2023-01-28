package com.example.data.entity

import org.junit.Assert
import org.junit.Test

/**
 * Created by TC on 27/01/2023.
 */

class TrackEntityTest {
    @Test
    fun `track has lyrics`() {
        val track = TrackEntity(
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
            images = TrackImages(
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

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `track has no lyrics case sections is null`() {
        val track = TrackEntity(
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
            images = TrackImages(
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

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `track has no lyrics case sections have no element lyrics`() {
        val track = TrackEntity(
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
            images = TrackImages(
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

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `track has media uri`() {
        val track = TrackEntity(
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
            images = TrackImages(
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

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `track has no media uri case hub is null`() {
        val track = TrackEntity(
            alias = null,
            artists = listOf(),
            genres = Genres("POP"),
            hub = null,
            images = TrackImages(
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

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `track has no media uri case hub has no element uri`() {
        val track = TrackEntity(
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
            images = TrackImages(
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

        Assert.assertEquals(expectedResult, actualResult)
    }
}
