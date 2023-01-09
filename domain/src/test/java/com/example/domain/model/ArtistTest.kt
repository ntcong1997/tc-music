package com.example.domain.model

import com.example.model.Artist
import com.example.model.Artwork
import com.example.model.Attributes
import com.example.model.Data
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by TC on 06/01/2023.
 */

class ArtistTest {
    @Test
    fun `artist does not have data param but has name and avatar param`() {
        val artist = Artist(
            adamid = "1",
            alias = null,
            avatar = "https://",
            data = null,
            name = "Bruno"
        )

        assertEquals("Bruno", artist.artistName)
        assertEquals("https://", artist.artistAvatar)
    }

    @Test
    fun `artist does not have name and avatar param but has data param`() {
        val artist = Artist(
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
                    type = null
                )
            ),
            name = null
        )

        assertEquals("Bruno", artist.artistName)
        assertEquals("https://", artist.artistAvatar)
    }

    @Test
    fun `artist has short name 1 char`() {
        val artist = Artist(
            adamid = "1",
            alias = null,
            avatar = "https://",
            data = null,
            name = "Bruno"
        )

        assertEquals("B", artist.shortArtistName)
    }

    @Test
    fun `artist has short name 2 char`() {
        val artist = Artist(
            adamid = "1",
            alias = null,
            avatar = "https://",
            data = null,
            name = "One Direction"
        )

        assertEquals("OD", artist.shortArtistName)
    }
}
