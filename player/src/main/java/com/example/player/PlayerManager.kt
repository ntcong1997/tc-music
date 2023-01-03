package com.example.player

import android.content.ComponentName
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.example.domain.player.Player
import com.example.model.PlayingMediaInfo
import com.example.model.Track
import com.example.player.data.PlayerData
import com.example.player.service.MusicPlayerService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import timber.log.Timber

/**
 * Created by TC on 13/12/2022.
 */

class PlayerManager @Inject constructor(
    private val playerData: PlayerData,
    @ApplicationContext private val context: Context
) : Player {
    private val _playingMediaInfo = MutableSharedFlow<PlayingMediaInfo?>(replay = 1)
    private val _isPlaying = MutableSharedFlow<Boolean>(replay = 1)
    private val _duration = MutableSharedFlow<Long>(replay = 1)
    private val _progress = MutableSharedFlow<Long>(replay = 1)

    private var mediaBrowser: MediaBrowserCompat? = null
    private var mediaController: MediaControllerCompat? = null

    private var playWhenReady = false

    private val mediaControllerCallback = object : MediaControllerCompat.Callback() {
        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
            super.onPlaybackStateChanged(state)
            Timber.d("$TAG: onPlaybackStateChanged $state")
            when (state?.state) {
                PlaybackStateCompat.STATE_PLAYING -> _isPlaying.tryEmit(true)
                PlaybackStateCompat.STATE_PAUSED -> _isPlaying.tryEmit(false)
            }

            _progress.tryEmit(state?.position ?: 0L)
        }

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
            super.onMetadataChanged(metadata)

            val id = metadata?.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID)
            val title = metadata?.getString(MediaMetadataCompat.METADATA_KEY_TITLE)
            val artist = metadata?.getString(MediaMetadataCompat.METADATA_KEY_ARTIST)
            val coverArt = metadata?.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI)
            val duration = metadata?.getLong(MediaMetadataCompat.METADATA_KEY_DURATION)
            Timber.d("$TAG: onMetadataChanged $id, $title, $artist, $coverArt $duration")

            _playingMediaInfo.tryEmit(PlayingMediaInfo(id, artist, coverArt, title))
            _duration.tryEmit(duration ?: 0L)
        }
    }

    private val mediaBrowserConnectionCallback = object : MediaBrowserCompat.ConnectionCallback() {
        override fun onConnected() {
            super.onConnected()

            mediaBrowser?.let {
                mediaController = MediaControllerCompat(context, it.sessionToken)
                mediaController?.registerCallback(mediaControllerCallback)
                it.subscribe(it.root, mediaBrowserSubscriptionCallback)
                mediaBrowserSubscriptionCallback.onChildrenLoaded(it.root, playerData.playerResources())
            }
        }
    }

    private val mediaBrowserSubscriptionCallback = object : MediaBrowserCompat.SubscriptionCallback() {
        override fun onChildrenLoaded(
            parentId: String,
            children: MutableList<MediaBrowserCompat.MediaItem>
        ) {
            super.onChildrenLoaded(parentId, children)

            for (mediaItem in children) {
                mediaController?.addQueueItem(mediaItem.description)
            }
            mediaController?.transportControls?.skipToQueueItem(playerData.startPlayingId)

            if (mediaController?.shuffleMode == PlaybackStateCompat.SHUFFLE_MODE_ALL) {
                mediaController?.transportControls?.setShuffleMode(PlaybackStateCompat.SHUFFLE_MODE_ALL)
            }

            mediaController?.transportControls?.prepare()

            if (playWhenReady) {
                mediaController?.transportControls?.play()
                playWhenReady = false
            }
        }
    }

    override fun setPlaylistAndPlay(playlist: List<Track>, startPlayingId: Long) {
        playerData.setPlaylist(playlist, startPlayingId)
        playWhenReady = true
        if (mediaBrowser == null || mediaBrowser?.isConnected == false) {
            connectMediaBrowser()
        } else {
            mediaController?.transportControls?.stop()
            mediaBrowser?.let {
                mediaBrowserSubscriptionCallback.onChildrenLoaded(it.root, playerData.playerResources())
            }
        }
    }

    override fun play() {
        mediaController?.transportControls?.play()
    }

    override fun pause() {
        mediaController?.transportControls?.pause()
    }

    override fun skipBackwards() {
        mediaController?.transportControls?.skipToPrevious()
    }

    override fun skipForward() {
        mediaController?.transportControls?.skipToNext()
    }

    override fun seekTo(position: Long) {
        _progress.tryEmit(position)
        mediaController?.transportControls?.seekTo(position)
    }

    override val playingMediaInfo: SharedFlow<PlayingMediaInfo?>
        get() = _playingMediaInfo.asSharedFlow()

    override val isPlaying: SharedFlow<Boolean>
        get() = _isPlaying.asSharedFlow()

    override val duration: SharedFlow<Long>
        get() = _duration.asSharedFlow()

    override val progress: SharedFlow<Long>
        get() = _progress.asSharedFlow()

    private fun connectMediaBrowser() {
        Handler(Looper.getMainLooper()).post {
            mediaBrowser = MediaBrowserCompat(
                context,
                ComponentName(context, MusicPlayerService::class.java),
                mediaBrowserConnectionCallback,
                null
            )
            mediaBrowser?.connect()
        }
    }

    companion object {
        private const val TAG = "PlayerManager"
    }
}
