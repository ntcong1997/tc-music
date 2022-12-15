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
import com.example.player.data.PlayerData
import com.example.player.util.addDurationToMediaMetadata
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.ArrayList
import javax.inject.Inject
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

    private var player: ExoPlayer? = null

    private var serviceInStartedState = false

    private var lastKnownExoPlayerState: Int? = null

    private var preparedMedia: MediaMetadataCompat? = null

    private var durationSet = false

    private val playerProgressionRunnable = Runnable {
        if (lastKnownExoPlayerState != null) {
            handlePlaybackStateChange(lastKnownExoPlayerState!!)
        }
        postPlayerProgression()
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
        }
    }

    private var mediaSession: MediaSessionCompat? = null
    private val mediaSessionCallback = object : MediaSessionCompat.Callback() {
        private var playlist = ArrayList<MediaSessionCompat.QueueItem>()
        private var sortedPlaylist = ArrayList<MediaSessionCompat.QueueItem>()
        private var queueIndex = -1
        private var shouldContinue = false
        private var lastSkip = 0L
        private val isReadyToPlay: Boolean
            get() = !playlist.isEmpty()

        override fun onAddQueueItem(description: MediaDescriptionCompat) {
            playlist.add(MediaSessionCompat.QueueItem(description, description.hashCode().toLong()))
            queueIndex = if (queueIndex == -1) 0 else queueIndex
            val distinctPlaylist = playlist.distinctBy { it.description.mediaId }
            playlist.clear()
            playlist.addAll(distinctPlaylist)
        }

        override fun onRemoveQueueItem(description: MediaDescriptionCompat) {
            playlist.remove(MediaSessionCompat.QueueItem(description, description.hashCode().toLong()))
            queueIndex = if (playlist.isEmpty()) -1 else queueIndex
        }

        override fun onPrepare() {
            if (queueIndex < 0 && playlist.isEmpty()) {
                // Nothing to play.
                return
            }

            val mediaId = playlist[queueIndex].description.mediaId
            preparedMedia = playerData.getMediaMetadataById(mediaId)
            mediaSession?.setMetadata(preparedMedia)
            durationSet = false

            val uri = Uri.parse(preparedMedia?.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI))
            val mediaItem = MediaItem.fromUri(uri)
            player?.setMediaItem(mediaItem)
            player?.prepare()

            if (mediaSession?.isActive == false) {
                mediaSession?.isActive = true
            }
            shouldContinue = false
        }

        override fun onPlay() {
            Timber.d("$TAG: onPlay")
            if (!isReadyToPlay) {
                return
            }

            if (shouldContinue && preparedMedia != null) {
                player?.play()
                postPlayerProgression()
                shouldContinue = false
                return
            }

            if (preparedMedia == null) {
                onPrepare()
            }

            player?.play()
            postPlayerProgression()
            shouldContinue = false
        }

        override fun onPause() {
            Timber.d("$TAG: onPause")
            shouldContinue = true
            player?.pause()
            handler.removeCallbacks(playerProgressionRunnable)
        }

        override fun onStop() {
            player?.pause()
            handler.removeCallbacks(playerProgressionRunnable)
            mediaSession?.isActive = false
            playlist.clear()
            queueIndex = -1
            preparedMedia = null
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

            preparedMedia = null

            if (mediaSession?.controller?.repeatMode == PlaybackStateCompat.REPEAT_MODE_ALL) {
                queueIndex = ++queueIndex % playlist.size
            } else if (mediaSession?.controller?.repeatMode == PlaybackStateCompat.REPEAT_MODE_NONE) {
                queueIndex = ++queueIndex % playlist.size
                if (queueIndex == 0) {
                    onPause()
                    onPrepare()
                    return
                }
            }
            // REPEAT_MODE_ONE: nothing happens, don't change the queueIndex

            if (player?.isPlaying == true) {
                onPlay()
            } else {
                onPrepare()
            }
        }

        override fun onSkipToPrevious() {
            preparedMedia = null
            if (mediaSession?.controller?.repeatMode == PlaybackStateCompat.REPEAT_MODE_ALL) {
                queueIndex = if (queueIndex > 0) queueIndex - 1 else playlist.size - 1
            } else if (mediaSession?.controller?.repeatMode == PlaybackStateCompat.REPEAT_MODE_NONE) {
                queueIndex = if (queueIndex > 0) queueIndex - 1 else 0
            }
            // REPEAT_MODE_ONE: nothing happens, don't change the queueIndex

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
            if (shuffleMode == PlaybackStateCompat.SHUFFLE_MODE_ALL) {
                sortedPlaylist.clear()
                sortedPlaylist.addAll(playlist)
                val thisSong = playlist[queueIndex]
                playlist.remove(thisSong)
                playlist.shuffle()
                playlist.add(queueIndex, thisSong)
            } else if (shuffleMode == PlaybackStateCompat.SHUFFLE_MODE_NONE) {
                val thisSongIndex = sortedPlaylist.indexOf(playlist[queueIndex])
                playlist.clear()
                playlist.addAll(sortedPlaylist)
                queueIndex = thisSongIndex
            }
        }

        override fun onSetRepeatMode(repeatMode: Int) {
            mediaSession?.setRepeatMode(repeatMode)
        }

        override fun onSkipToQueueItem(id: Long) {
            super.onSkipToQueueItem(id)
            queueIndex = playlist.indexOf(playlist.first { it.description.mediaId?.toLong() == id })
        }
    }

    private val playerEventListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            Timber.d("$TAG: onIsPlayingChanged $isPlaying")
            lastKnownExoPlayerState = Player.STATE_READY
            handlePlaybackStateChange(lastKnownExoPlayerState!!)
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            Timber.d("$TAG: onPlaybackStateChanged $playbackState")
            if (playbackState == Player.STATE_READY && !durationSet) {
                mediaSession?.setMetadata(addDurationToMediaMetadata(preparedMedia, player?.duration ?: 0L))
                durationSet = true
            }

            lastKnownExoPlayerState = playbackState
            handlePlaybackStateChange(lastKnownExoPlayerState!!)
        }
    }

    private val handler = Handler(Looper.getMainLooper())

    private fun handlePlaybackStateChange(exoPlayerState: Int) {
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

    private fun postPlayerProgression() {
        handler.removeCallbacks(playerProgressionRunnable)
        handler.postDelayed(playerProgressionRunnable, 1000L)
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
        if (preparedMedia == null || sessionToken == null) {
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
            preparedMedia!!,
            state,
            sessionToken!!
        )
    }

    private fun updateNotificationForPause(state: PlaybackStateCompat) {
        if (preparedMedia == null || sessionToken == null) {
            return
        }
        mediaNotificationManager.createNotification(
            preparedMedia!!,
            state,
            sessionToken!!
        )
    }

    override fun onCreate() {
        super.onCreate()

        player = ExoPlayer.Builder(this).build()
        player?.addListener(playerEventListener)

        mediaSession = MediaSessionCompat(this, "TCMusicMediaSession").apply {
            setFlags(MediaSessionCompat.FLAG_HANDLES_QUEUE_COMMANDS)
        }
        mediaSession?.setCallback(mediaSessionCallback)
        sessionToken = mediaSession?.sessionToken
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.removeCallbacks(playerProgressionRunnable)

        player?.pause()
        player?.release()
        mediaSession?.release()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)

        mediaNotificationManager.cancelNotification()
        stopSelf()
        context.unbindService(serviceConnection)
    }

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot {
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