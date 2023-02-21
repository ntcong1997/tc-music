package com.example.tcmusic.player

import com.example.domain.player.Player
import com.example.model.PlayingMedia
import com.example.model.Track
import com.example.test.data.PlayingMediasInfo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Created by TC on 29/12/2022.
 */

class FakePlayer : Player {
    private val _playingMedia = MutableSharedFlow<PlayingMedia?>(replay = 1)
    private val _isPlaying = MutableSharedFlow<Boolean>(replay = 1)
    private val _duration = MutableSharedFlow<Long>(replay = 1)
    private val _progress = MutableSharedFlow<Long>(replay = 1)

    override fun setPlaylistAndPlay(playlist: List<Track>, startPlayingId: Long) {
        val playingTrack = playlist.first { it.id?.toLong() == startPlayingId }
        val playingMedia = PlayingMediasInfo.find { it.id == playingTrack.id }
        _playingMedia.tryEmit(playingMedia)
    }

    override fun play() {
        _isPlaying.tryEmit(true)
    }

    override fun pause() {
        _isPlaying.tryEmit(false)
    }

    override fun skipBackwards() {
    }

    override fun skipForward() {
    }

    override fun seekTo(position: Long) {
    }

    override val playingMedia: SharedFlow<PlayingMedia?>
        get() = _playingMedia.asSharedFlow()

    override val isPlaying: SharedFlow<Boolean>
        get() = _isPlaying.asSharedFlow()

    override val duration: SharedFlow<Long>
        get() = _duration.asSharedFlow()

    override val progress: SharedFlow<Long>
        get() = _progress.asSharedFlow()
}
