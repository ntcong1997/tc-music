package com.example.player.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.*
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.MediaBrowserServiceCompat
import com.example.domain.di.ApplicationScope
import com.example.player.adapter.ExoPlayerAdapter
import com.example.player.adapter.ExoPlayerStateChangeListener
import com.example.player.data.ImageLoader
import com.example.player.data.PlayerData
import com.example.player.util.addAlbumArtToMediaMetadata
import com.example.player.util.addDurationToMediaMetadata
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by TC on 13/12/2022.
 */

@AndroidEntryPoint
class MusicPlayerService : MediaBrowserServiceCompat() {
    @Inject
    lateinit var playerData: PlayerData

    @Inject
    lateinit var mediaNotificationManager: MediaNotificationManager

    @ApplicationContext
    @Inject
    lateinit var context: Context

    @ApplicationScope
    @Inject
    lateinit var coroutineScope: CoroutineScope

    private var player: ExoPlayerAdapter? = null

    private var serviceInStartedState = false

    private var durationSet = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
        }
    }

    private var mediaSession: MediaSessionCompat? = null
    private val mediaSessionCallback = object : MediaSessionCompat.Callback() {
        private var shouldContinue = false
        private var lastSkip = 0L

        override fun onAddQueueItem(description: MediaDescriptionCompat) {
            playerData.addPlaylistQueueItem(description)
        }

        override fun onRemoveQueueItem(description: MediaDescriptionCompat) {
            playerData.removePlaylistQueueItem(description)
        }

        override fun onPrepare() {
            if (playerData.playlistQueueIndex < 0 || playerData.playlist.isEmpty()) {
                // Nothing to prepare
                return
            }

            // Every time prepare media reset this var to get current media's duration
            durationSet = false

            playerData.prepareMedia()

            coroutineScope.launch {
                val albumArtUrl =
                    playerData.preparedMediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI)
                playerData.preparedMediaMetadata = addAlbumArtToMediaMetadata(
                    playerData.preparedMediaMetadata,
                    ImageLoader.loadImageAsync(this@MusicPlayerService, albumArtUrl)
                )
                mediaSession?.setMetadata(playerData.preparedMediaMetadata)

                player?.prepare(
                    Uri.parse(
                        playerData.preparedMediaMetadata?.getString(
                            MediaMetadataCompat.METADATA_KEY_MEDIA_URI
                        )
                    )
                )
            }

            if (mediaSession?.isActive == false) {
                mediaSession?.isActive = true
            }
            shouldContinue = false
        }

        override fun onPlay() {
            Timber.d("$TAG: onPlay")

            if (shouldContinue && playerData.preparedMediaMetadata != null) {
                player?.play()
                shouldContinue = false
                return
            }

            if (playerData.preparedMediaMetadata == null) {
                onPrepare()
            }

            player?.play()
            shouldContinue = false
        }

        override fun onPause() {
            Timber.d("$TAG: onPause")
            shouldContinue = true
            player?.pause()
        }

        override fun onStop() {
            playerData.resetMedia()
            mediaSession?.isActive = false
            shouldContinue = false
            mediaNotificationManager.cancelNotification()
            stopSelf()
        }

        override fun onSkipToNext() {
            // This is an ugly workaround for a bug in ExoPlayer that sometimes skip twice fast
            val now = System.currentTimeMillis()
            if (now - lastSkip < 100) {
                lastSkip = now
                return
            }
            lastSkip = now

            playerData.skipToNext()

            if (mediaSession?.controller?.repeatMode == PlaybackStateCompat.REPEAT_MODE_NONE && playerData.playlistQueueIndex == 0) {
                // REPEAT_MODE_ONE: nothing happens, don't change the queueIndex
                onPause()
                onPrepare()
                return
            }

            if (player?.isPlaying == true) {
                onPlay()
            } else {
                onPrepare()
            }
        }

        override fun onSkipToPrevious() {
            playerData.skipToPrevious(mediaSession?.controller?.repeatMode)

            if (player?.isPlaying == true) {
                onPlay()
            } else {
                onPrepare()
            }
        }

        override fun onSeekTo(pos: Long) {
            player?.seekTo(pos)
        }

        override fun onSetShuffleMode(shuffleMode: Int) {
            mediaSession?.setShuffleMode(shuffleMode)
            playerData.shuffleModeChange(shuffleMode)
        }

        override fun onSetRepeatMode(repeatMode: Int) {
            mediaSession?.setRepeatMode(repeatMode)
        }

        override fun onSkipToQueueItem(id: Long) {
            super.onSkipToQueueItem(id)
            playerData.skipToQueueItem(id)
        }
    }

    private fun handleExoPlayerStateChange(exoPlayerState: Int) {
        val state = getState(exoPlayerState)

        val result = PlaybackStateCompat.Builder()
            .setActions(getAvailableActions(state))
            .setState(state, player?.currentPosition ?: 0L, 1.0f, SystemClock.elapsedRealtime())
            .build()

        Timber.d("$TAG: onPlaybackStateChanged $state $result ${player?.currentPosition}")
        when (result.state) {
            PlaybackStateCompat.STATE_PLAYING -> moveServiceToStartedState(result)
            PlaybackStateCompat.STATE_PAUSED -> updateNotificationForPause(result)
        }

        mediaSession?.setPlaybackState(result)
        if (exoPlayerState == Player.STATE_ENDED) {
            mediaSession?.controller?.transportControls?.skipToNext()
        }
    }

    private fun getState(exoPlayerState: Int): Int {
        return when (exoPlayerState) {
            Player.STATE_IDLE -> PlaybackStateCompat.STATE_PAUSED
            Player.STATE_BUFFERING -> PlaybackStateCompat.STATE_BUFFERING
            Player.STATE_READY -> if (player?.isPlaying == true)
                PlaybackStateCompat.STATE_PLAYING
            else
                PlaybackStateCompat.STATE_PAUSED
            Player.STATE_ENDED -> PlaybackStateCompat.STATE_PAUSED
            else -> PlaybackStateCompat.STATE_NONE
        }
    }

    private fun getAvailableActions(state: Int): Long {
        var actions = (
            PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID
                or PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH
                or PlaybackStateCompat.ACTION_SKIP_TO_NEXT
                or PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
            )
        actions = when (state) {
            PlaybackStateCompat.STATE_STOPPED -> actions or (PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PAUSE)
            PlaybackStateCompat.STATE_PLAYING -> actions or (
                PlaybackStateCompat.ACTION_STOP
                    or PlaybackStateCompat.ACTION_PAUSE
                    or PlaybackStateCompat.ACTION_SEEK_TO
                )
            PlaybackStateCompat.STATE_PAUSED -> actions or (PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_STOP)
            else -> actions or (
                PlaybackStateCompat.ACTION_PLAY
                    or PlaybackStateCompat.ACTION_PLAY_PAUSE
                    or PlaybackStateCompat.ACTION_STOP
                    or PlaybackStateCompat.ACTION_PAUSE
                )
        }
        return actions
    }

    private fun moveServiceToStartedState(state: PlaybackStateCompat) {
        if (playerData.preparedMediaMetadata == null || sessionToken == null) {
            return
        }

        if (!serviceInStartedState) {
            val intent = Intent(this@MusicPlayerService, MusicPlayerService::class.java)
            context.startService(intent)
            context.bindService(
                intent,
                serviceConnection,
                Context.BIND_AUTO_CREATE
            )
            serviceInStartedState = true
        }

        mediaNotificationManager.createNotification(
            playerData.preparedMediaMetadata!!,
            state,
            sessionToken!!
        )
    }

    private fun updateNotificationForPause(state: PlaybackStateCompat) {
        if (playerData.preparedMediaMetadata == null || sessionToken == null) {
            return
        }
        mediaNotificationManager.createNotification(
            playerData.preparedMediaMetadata!!,
            state,
            sessionToken!!
        )
    }

    override fun onCreate() {
        super.onCreate()

        player = ExoPlayerAdapter(
            context,
            object : ExoPlayerStateChangeListener {
                override fun onStateChange(playbackState: Int) {
                    setMediaMetadataDuration(playbackState)
                    handleExoPlayerStateChange(playbackState)
                }
            }
        )

        mediaSession = MediaSessionCompat(this, "TCMusicMediaSession").apply {
            setFlags(MediaSessionCompat.FLAG_HANDLES_QUEUE_COMMANDS)
        }
        mediaSession?.setCallback(mediaSessionCallback)
        sessionToken = mediaSession?.sessionToken
    }

    private fun setMediaMetadataDuration(playbackState: Int) {
        if (playbackState == Player.STATE_READY && !durationSet) {
            val duration = player?.duration ?: 0L
            if (duration > 0L) {
                mediaSession?.setMetadata(
                    addDurationToMediaMetadata(
                        playerData.preparedMediaMetadata,
                        duration
                    )
                )
                durationSet = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        player?.stop()
        mediaSession?.release()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)

        mediaNotificationManager.cancelNotification()
        stopSelf()
        context.unbindService(serviceConnection)
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot {
        return BrowserRoot("player", null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        result.sendResult(playerData.playerResources())
    }

    companion object {
        private const val TAG = "MusicPlayerService"
    }
}
