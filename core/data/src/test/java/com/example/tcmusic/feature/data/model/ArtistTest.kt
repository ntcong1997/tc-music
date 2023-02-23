package com.example.tcmusic.feature.data.model

import com.example.tcmusic.core.data.model.toArtist
import com.example.tcmusic.core.data.model.toTrack
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.network.model.*
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by TC on 22/02/2023.
 */
class ArtistTest {
    @Test
    fun `NetworkArtist does not have field data map to Artist`() {
        val networkArtist = NetworkArtist(
            adamid = "1",
            alias = null,
            avatar = "avatar",
            data = null,
            id = null,
            name = "name"
        )
        val artist = networkArtist.toArtist()

        assertEquals("1", artist.id)
        assertEquals("name", artist.name)
        assertEquals("avatar", artist.avatar)
        assertEquals(listOf(), artist.topSongs)
    }

    @Test
    fun `NetworkArtist has field data map to Artist`() {
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
        val artist = networkArtist.toArtist()

        assertEquals("1", artist.id)
        assertEquals("name", artist.name)
        assertEquals("avatar", artist.avatar)
        assertEquals(listOf(Track(
            id = "1",
            image = "trackImage",
            genre = "trackGenre",
            lyrics = listOf(),
            title = "trackTitle",
            subTitle = "trackSubTitle",
            uri = "trackUri",
            version = "2"
        )), artist.topSongs)
    }
}