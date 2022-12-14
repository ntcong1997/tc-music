package com.example.player.data

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import com.example.model.Track
import com.example.player.util.toMediaMetadata
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by TC on 13/12/2022.
 */

@Singleton
class PlayerData @Inject constructor(
    private val imageCache: ImageCache
) {
    private var playlist: List<MediaMetadataCompat> = emptyList()
    var startPlayingId = 0L

    suspend fun setPlaylist(playlist: List<Track>, startPlayingId: Long) {
        this.startPlayingId = startPlayingId
        imageCache.loadAllImagesAsync(
            playlist.map {
                it.images?.coverart
            }.filterNotNull()
        )
        this.playlist = playlist.toMediaMetadata(imageCache)
    }

    fun playerResources(): MutableList<MediaBrowserCompat.MediaItem> {
        val result = playlist.map {
            MediaBrowserCompat.MediaItem(
                it.description, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
            )
        }
        return result.toMutableList()
    }

    fun getMediaMetadataById(id: String?): MediaMetadataCompat? {
        playlist.onEach {
            if (it.description.mediaId.equals(id)) {
                return it
            }
        }
        return null
    }
}