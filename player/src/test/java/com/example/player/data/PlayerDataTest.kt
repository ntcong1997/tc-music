package com.example.player.data

import android.content.Context
import android.support.v4.media.MediaDescriptionCompat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

/**
 * Created by TC on 21/12/2022.
 */

class PlayerDataTest {
    private lateinit var playerData: PlayerData

    @Before
    fun setup() {
        val context = mock<Context> { }
        val imageCache = ImageCache(context)

        playerData = PlayerData(imageCache)
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
}