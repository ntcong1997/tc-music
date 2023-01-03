package com.example.player.data

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.example.model.Track
import com.example.player.util.toMediaMetadata
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by TC on 13/12/2022.
 */

@Singleton
class PlayerData @Inject constructor(

) {
    private var playlistResources: List<MediaMetadataCompat> = emptyList()
    var startPlayingId = 0L

    // Original playlist without modified
    private val originalPlaylist = ArrayList<MediaSessionCompat.QueueItem>()
    // Modified playlist by shuffle mode
    // Playlist to play
    val playlist = ArrayList<MediaSessionCompat.QueueItem>()
    // Playlist queue index
    // If -1 that means currently player not set any playlist
    var playlistQueueIndex = -1

    var preparedMediaMetadata: MediaMetadataCompat? = null

    fun setPlaylist(playlist: List<Track>, startPlayingId: Long) {
        this.startPlayingId = startPlayingId
        this.playlistResources = playlist.toMediaMetadata()
    }

    fun playerResources(): MutableList<MediaBrowserCompat.MediaItem> {
        return playlistResources.map {
            MediaBrowserCompat.MediaItem(
                it.description, MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
            )
        }.toMutableList()
    }

    private fun getMediaMetadataById(id: String?): MediaMetadataCompat? {
        playlistResources.onEach {
            if (it.description.mediaId.equals(id)) {
                return it
            }
        }
        return null
    }

    fun addPlaylistQueueItem(description: MediaDescriptionCompat) {
        val queueItem = MediaSessionCompat.QueueItem(description, description.hashCode().toLong())
        originalPlaylist.add(queueItem)
        playlist.add(queueItem)

        playlistQueueIndex = if (playlistQueueIndex == -1) 0 else playlistQueueIndex
    }

    fun removePlaylistQueueItem(description: MediaDescriptionCompat) {
        originalPlaylist.removeIf { it.queueId == description.hashCode().toLong() }
        playlist.removeIf { it.queueId == description.hashCode().toLong() }

        playlistQueueIndex = if (playlist.isEmpty()) -1 else playlistQueueIndex
    }

    fun prepareMedia() {
        val mediaId = playlist[playlistQueueIndex].description.mediaId
        preparedMediaMetadata = getMediaMetadataById(mediaId)
    }

    fun resetMedia() {
        originalPlaylist.clear()
        playlist.clear()

        preparedMediaMetadata = null
        playlistQueueIndex = -1
    }

    fun skipToNext() {
        preparedMediaMetadata = null
        playlistQueueIndex = ++playlistQueueIndex % playlist.size
    }

    fun skipToPrevious(repeatMode: Int?) {
        preparedMediaMetadata = null
        if (repeatMode == PlaybackStateCompat.REPEAT_MODE_ALL) {
            playlistQueueIndex = if (playlistQueueIndex > 0) playlistQueueIndex - 1 else playlist.size - 1
        } else if (repeatMode == PlaybackStateCompat.REPEAT_MODE_NONE) {
            playlistQueueIndex = if (playlistQueueIndex > 0) playlistQueueIndex - 1 else 0
        }
        // REPEAT_MODE_ONE: nothing happens, don't change the playlistQueueIndex
    }

    fun skipToQueueItem(id: Long) {
        playlistQueueIndex = playlist.indexOf(playlist.first { it.description.mediaId?.toLong() == id })
    }

    fun shuffleModeChange(shuffleMode: Int) {
        if (shuffleMode == PlaybackStateCompat.SHUFFLE_MODE_ALL) {
            val thisSong = playlist[playlistQueueIndex]
            playlist.remove(thisSong)
            playlist.shuffle()
            playlist.add(playlistQueueIndex, thisSong)
        } else if (shuffleMode == PlaybackStateCompat.SHUFFLE_MODE_NONE) {
            val thisSongIndex = originalPlaylist.indexOf(playlist[playlistQueueIndex])
            playlist.clear()
            playlist.addAll(originalPlaylist)
            playlistQueueIndex = thisSongIndex
        }
    }
}
