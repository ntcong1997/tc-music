package com.example.player.data

import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.PlaybackStateCompat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by TC on 21/12/2022.
 */

class PlayerDataTest {
    private lateinit var playerData: PlayerData

    @Before
    fun setup() {
        playerData = PlayerData()
    }

    @Test
    fun `add playlist queue item`() {
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())

        assertEquals(playerData.playlist.size, 1)
        assertEquals(playerData.playlistQueueIndex, 0)

        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())

        assertEquals(2, playerData.playlist.size)
        assertEquals(0, playerData.playlistQueueIndex)
    }

    @Test
    fun `remove playlist queue item`() {
        val mediaDescriptionCompat1 = MediaDescriptionCompat.Builder().build()
        playerData.addPlaylistQueueItem(mediaDescriptionCompat1)
        val mediaDescriptionCompat2 = MediaDescriptionCompat.Builder().build()
        playerData.addPlaylistQueueItem(mediaDescriptionCompat2)

        playerData.removePlaylistQueueItem(mediaDescriptionCompat1)

        assertEquals(1, playerData.playlist.size)
        assertEquals(0, playerData.playlistQueueIndex)

        playerData.removePlaylistQueueItem(mediaDescriptionCompat2)

        assertEquals(0, playerData.playlist.size)
        assertEquals(-1, playerData.playlistQueueIndex)
    }

    @Test
    fun `skip to next`() {
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())

        playerData.skipToNext()

        assertEquals(1, playerData.playlistQueueIndex)

        playerData.skipToNext()

        assertEquals(0, playerData.playlistQueueIndex)
    }

    @Test
    fun `skip to previous repeat mode all`() {
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())

        playerData.skipToPrevious(PlaybackStateCompat.REPEAT_MODE_ALL)

        assertEquals(1, playerData.playlistQueueIndex)

        playerData.skipToPrevious(PlaybackStateCompat.REPEAT_MODE_ALL)

        assertEquals(0, playerData.playlistQueueIndex)
    }

    @Test
    fun `skip to previous repeat mode none`() {
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())

        playerData.skipToPrevious(PlaybackStateCompat.REPEAT_MODE_NONE)

        assertEquals(0, playerData.playlistQueueIndex)
    }

    @Test
    fun `skip to item`() {
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().setMediaId("1").build())
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().setMediaId("2").build())
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().setMediaId("3").build())

        assertEquals(3, playerData.playlist.size)
        assertEquals(0, playerData.playlistQueueIndex)

        playerData.skipToQueueItem(2L)

        assertEquals(1, playerData.playlistQueueIndex)

        playerData.skipToQueueItem(1L)

        assertEquals(0, playerData.playlistQueueIndex)

        playerData.skipToQueueItem(3L)

        assertEquals(2, playerData.playlistQueueIndex)
    }
}
