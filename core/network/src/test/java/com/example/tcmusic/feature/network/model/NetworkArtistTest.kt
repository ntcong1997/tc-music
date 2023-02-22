package com.example.tcmusic.feature.network.model

import com.example.tcmusic.core.network.model.Artwork
import com.example.tcmusic.core.network.model.Attributes
import com.example.tcmusic.core.network.model.Data
import com.example.tcmusic.core.network.model.NetworkArtist
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by TC on 22/02/2023.
 */
class NetworkArtistTest {
    @Test
    fun `NetworkArtist does not have field data`() {
        val networkArtist = NetworkArtist(
            adamid = "1",
            alias = null,
            avatar = "avatar",
            data = null,
            name = "name"
        )

        assertEquals("1", networkArtist.id)
        assertEquals("avatar", networkArtist.artistAvatar)
        assertEquals("name", networkArtist.artistName)
    }

    @Test
    fun `NetworkArtist has field data`() {
        val networkArtist = NetworkArtist(
            adamid = null,
            alias = null,
            avatar = null,
            data = listOf(
                Data(
                    attributes = Attributes(
                        artistBio = null,
                        artwork = Artwork(
                            url = "avatar"
                        ),
                        genreNames = null,
                        name = "name",
                        origin = null,
                        url = null
                    ),
                    href = null,
                    id = "1",
                    relationships = null,
                    type = null,
                    views = null
                )
            ),
            name = null
        )

        assertEquals("1", networkArtist.id)
        assertEquals("avatar", networkArtist.artistAvatar)
        assertEquals("name", networkArtist.artistName)
    }
}