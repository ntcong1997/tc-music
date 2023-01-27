package com.example.data.entity

import org.junit.Assert
import org.junit.Test

/**
 * Created by TC on 27/01/2023.
 */

class ArtistEntityTest {
    @Test
    fun `artist does not have data param but has name and avatar param`() {
        val artist = ArtistEntity(
            adamid = "1",
            alias = null,
            avatar = "https://",
            data = null,
            name = "Bruno"
        )

        Assert.assertEquals("Bruno", artist.artistName)
        Assert.assertEquals("https://", artist.artistAvatar)
    }

    @Test
    fun `artist does not have name and avatar param but has data param`() {
        val artist = ArtistEntity(
            adamid = "1",
            alias = null,
            avatar = null,
            data = listOf(
                Data(
                    attributes = Attributes(
                        artistBio = null,
                        artwork = Artwork(
                            url = "https://"
                        ),
                        genreNames = null,
                        name = "Bruno",
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

        Assert.assertEquals("Bruno", artist.artistName)
        Assert.assertEquals("https://", artist.artistAvatar)
    }
}
