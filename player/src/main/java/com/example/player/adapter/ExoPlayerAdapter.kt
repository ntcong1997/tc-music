package com.example.player.adapter

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import timber.log.Timber

/**
 * Created by TC on 16/12/2022.
 */
class ExoPlayerAdapter(
    private val context: Context,
    private val listener: ExoPlayerStateChangeListener
) {
    private val exoPlayer = ExoPlayer.Builder(context).build().apply {
        addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                Timber.d("$TAG: onIsPlayingChanged $isPlaying")
                lastKnownExoPlayerState = playbackState
                listener.onStateChange(playbackState)
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                Timber.d("$TAG: onPlaybackStateChanged $playbackState")
                lastKnownExoPlayerState = playbackState
                listener.onStateChange(playbackState)
            }
        })
    }

    private var lastKnownExoPlayerState: Int? = null

    val isPlaying: Boolean
        get() = exoPlayer.isPlaying

    val currentPosition: Long
        get() = exoPlayer.currentPosition

    val duration: Long
        get() = exoPlayer.duration

    private val playerProgressionRunnable = Runnable {
        lastKnownExoPlayerState?.let {
            listener.onStateChange(it)
        }
        postPlayerProgression()
    }

    private val handler = Handler(Looper.getMainLooper())

    private fun postPlayerProgression() {
        handler.removeCallbacks(playerProgressionRunnable)
        handler.postDelayed(playerProgressionRunnable, 1000L)
    }

    fun prepare(uri: Uri) {
        handler.post {
            val mediaItem = MediaItem.fromUri(uri)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
        }
    }

    fun play() {
        exoPlayer.playWhenReady = true
        postPlayerProgression()
    }

    fun pause() {
        exoPlayer.playWhenReady = false
        handler.removeCallbacks(playerProgressionRunnable)
    }

    fun stop() {
        exoPlayer.playWhenReady = false
        handler.removeCallbacks(playerProgressionRunnable)
        exoPlayer.release()
    }

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    companion object {
        private const val TAG = "ExoPlayerAdapter"
    }
}

interface ExoPlayerStateChangeListener {
    fun onStateChange(playbackState: Int)
}
