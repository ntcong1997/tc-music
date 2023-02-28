package com.example.tcmusic.core.network.model

import com.example.tcmusic.core.network.model.Action
import com.example.tcmusic.core.network.model.Hub
import com.example.tcmusic.core.network.model.NetworkTrackV1
import com.example.tcmusic.core.network.model.Section
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by TC on 22/02/2023.
 */

class NetworkTrackV1Test {
    @Test
    fun `NetworkTrackV1 has lyrics in field section`() {
        val networkTrackV1 = NetworkTrackV1(
            alias = null,
            artists = null,
            genres = null,
            hub = null,
            images = null,
            key = null,
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
            subtitle = null,
            title = null,
            type = null,
            url = null,
            urlparams = null
        )

        assertEquals(listOf("A", "B", "C"), networkTrackV1.lyrics)
    }

    @Test
    fun `NetworkTrackV1 has no lyrics case sections is null`() {
        val networkTrackV1 = NetworkTrackV1(
            alias = null,
            artists = null,
            genres = null,
            hub = null,
            images = null,
            key = null,
            layout = null,
            releasedate = null,
            sections = null,
            share = null,
            subtitle = null,
            title = null,
            type = null,
            url = null,
            urlparams = null
        )

        assertEquals(listOf<String>(), networkTrackV1.lyrics)
    }

    @Test
    fun `NetworkTrackV1 has no lyrics case sections have no element lyrics`() {
        val networkTrackV1 = NetworkTrackV1(
            alias = null,
            artists = null,
            genres = null,
            hub = null,
            images = null,
            key = null,
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
            subtitle = null,
            title = null,
            type = null,
            url = null,
            urlparams = null
        )

        assertEquals(listOf<String>(), networkTrackV1.lyrics)
    }

    @Test
    fun `NetworkTrackV1 has media uri`() {
        val networkTrackV1 = NetworkTrackV1(
            alias = null,
            artists = null,
            genres = null,
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
            images = null,
            key = null,
            layout = null,
            releasedate = null,
            sections = null,
            share = null,
            subtitle = null,
            title = null,
            type = null,
            url = null,
            urlparams = null
        )

        assertEquals("uri", networkTrackV1.mediaUri)
    }

    @Test
    fun `NetworkTrackV1 has no media uri case hub is null`() {
        val networkTrackV1 = NetworkTrackV1(
            alias = null,
            artists = null,
            genres = null,
            hub = null,
            images = null,
            key = "1",
            layout = null,
            releasedate = null,
            sections = null,
            share = null,
            subtitle = null,
            title = null,
            type = null,
            url = null,
            urlparams = null
        )

        assertEquals(null, networkTrackV1.mediaUri)
    }

    @Test
    fun `NetworkTrackV1 has no media uri case hub has no element uri`() {
        val networkTrackV1 = NetworkTrackV1(
            alias = null,
            artists = null,
            genres = null,
            hub = Hub(
                actions = listOf(
                    Action(
                        id = null,
                        name = null,
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
            images = null,
            key = null,
            layout = null,
            releasedate = null,
            sections = null,
            share = null,
            subtitle = null,
            title = null,
            type = null,
            url = null,
            urlparams = null
        )

        assertEquals(null, networkTrackV1.mediaUri)
    }
}
