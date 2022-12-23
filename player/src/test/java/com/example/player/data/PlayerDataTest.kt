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

        assertEquals(playerData.playlist.size, 2)
        assertEquals(playerData.playlistQueueIndex, 0)
    }

    @Test
    fun `remove playlist queue item`() {
        val mediaDescriptionCompat1 = MediaDescriptionCompat.Builder().build()
        playerData.addPlaylistQueueItem(mediaDescriptionCompat1)
        val mediaDescriptionCompat2 = MediaDescriptionCompat.Builder().build()
        playerData.addPlaylistQueueItem(mediaDescriptionCompat2)

        playerData.removePlaylistQueueItem(mediaDescriptionCompat1)

        assertEquals(playerData.playlist.size, 1)
        assertEquals(playerData.playlistQueueIndex, 0)

        playerData.removePlaylistQueueItem(mediaDescriptionCompat2)

        assertEquals(playerData.playlist.size, 0)
        assertEquals(playerData.playlistQueueIndex, -1)
    }

    @Test
    fun `skip to next`() {
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())

        playerData.skipToNext()

        assertEquals(playerData.playlistQueueIndex, 1)

        playerData.skipToNext()

        assertEquals(playerData.playlistQueueIndex, 0)
    }

    @Test
    fun `skip to previous repeat mode all`() {
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())

        playerData.skipToPrevious(PlaybackStateCompat.REPEAT_MODE_ALL)

        assertEquals(playerData.playlistQueueIndex, 1)

        playerData.skipToPrevious(PlaybackStateCompat.REPEAT_MODE_ALL)

        assertEquals(playerData.playlistQueueIndex, 0)
    }

    @Test
    fun `skip to previous repeat mode none`() {
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().build())

        playerData.skipToPrevious(PlaybackStateCompat.REPEAT_MODE_NONE)

        assertEquals(playerData.playlistQueueIndex, 0)
    }

    @Test
    fun `skip to item`() {
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().setMediaId("1").build())
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().setMediaId("2").build())
        playerData.addPlaylistQueueItem(MediaDescriptionCompat.Builder().setMediaId("3").build())

        assertEquals(playerData.playlist.size, 3)
        assertEquals(playerData.playlistQueueIndex, 0)

        playerData.skipToQueueItem(2L)

        assertEquals(playerData.playlistQueueIndex, 1)

        playerData.skipToQueueItem(1L)

        assertEquals(playerData.playlistQueueIndex, 0)

        playerData.skipToQueueItem(3L)

        assertEquals(playerData.playlistQueueIndex, 2)
    }
}
