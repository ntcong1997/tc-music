package com.example.tcmusic.core.network.model

import com.example.tcmusic.core.network.model.*
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
            id = null,
            name = "name"
        )

        assertEquals("1", networkArtist.artistId)
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
                    views = Views(
                        topSongs = TopSongs(
                            attributes = null,
                            data = listOf(
                                TopSongsData(
                                    attributes = TopSongsDataAttributes(
                                        albumName = null,
                                        artistName = "trackSubTitle",
                                        artwork = Artwork(
                                            url = "trackImage"
                                        ),
                                        audioLocale = null,
                                        audioTraits = null,
                                        composerName = null,
                                        discNumber = null,
                                        durationInMillis = null,
                                        genreNames = listOf("trackGenre"),
                                        hasLyrics = null,
                                        hasTimeSyncedLyrics = null,
                                        isAppleDigitalMaster = null,
                                        isMasteredForItunes = null,
                                        isVocalAttenuationAllowed = null,
                                        isrc = null,
                                        name = "trackTitle",
                                        previews = listOf(
                                            Preview(
                                                url = "trackUri"
                                            )
                                        ),
                                        releaseDate = null,
                                        trackNumber = null,
                                        url = null
                                    ),
                                    href = null,
                                    id = "1",
                                    type = null
                                )
                            ),
                            href = null,
                            next = null
                        )
                    )
                )
            ),
            id = null,
            name = null
        )

        assertEquals("1", networkArtist.artistId)
        assertEquals("avatar", networkArtist.artistAvatar)
        assertEquals("name", networkArtist.artistName)
        assertEquals(
            listOf(
                TopSongsData(
                    attributes = TopSongsDataAttributes(
                        albumName = null,
                        artistName = "trackSubTitle",
                        artwork = Artwork(
                            url = "trackImage"
                        ),
                        audioLocale = null,
                        audioTraits = null,
                        composerName = null,
                        discNumber = null,
                        durationInMillis = null,
                        genreNames = listOf("trackGenre"),
                        hasLyrics = null,
                        hasTimeSyncedLyrics = null,
                        isAppleDigitalMaster = null,
                        isMasteredForItunes = null,
                        isVocalAttenuationAllowed = null,
                        isrc = null,
                        name = "trackTitle",
                        previews = listOf(
                            Preview(
                                url = "trackUri"
                            )
                        ),
                        releaseDate = null,
                        trackNumber = null,
                        url = null
                    ),
                    href = null,
                    id = "1",
                    type = null
                )
            ),
            networkArtist.topSongs
        )
    }
}
