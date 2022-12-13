package com.example.player.service

import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.ExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Created by TC on 13/12/2022.
 */

@AndroidEntryPoint
class MusicPlayerService : MediaBrowserServiceCompat() {
    private var player: ExoPlayer? = null

    private var mediaSession: MediaSessionCompat? = null
    private val mediaSessionCallback = object : MediaSessionCompat.Callback() {
        override fun onPrepare() {
            Timber.d("$TAG: onPrepare")
        }

        override fun onPlay() {
            Timber.d("$TAG: onPlay")
        }

        override fun onPause() {
            Timber.d("$TAG: onPause")
        }
    }

    override fun onCreate() {
        super.onCreate()

        player = ExoPlayer.Builder(this).build()

        mediaSession = MediaSessionCompat(this, "TCMusicMediaSession")
        mediaSession?.setCallback(mediaSessionCallback)
        sessionToken = mediaSession?.sessionToken
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaSession?.release()
    }

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot {
        return BrowserRoot("player", null)
    }

    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaBrowserCompat.MediaItem>>) {

    }

    companion object {
        private const val TAG = "MusicPlayerService"
    }
}