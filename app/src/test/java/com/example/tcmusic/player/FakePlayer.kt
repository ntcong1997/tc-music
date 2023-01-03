package com.example.tcmusic.player

import com.example.domain.player.Player
import com.example.model.PlayingMediaInfo
import com.example.model.Track
import com.example.test.data.PlayingMediaInfo
import kotlinx.coroutines.flow.*

/**
 * Created by TC on 29/12/2022.
 */

class FakePlayer : Player {
    private val _playingMediaInfo = MutableSharedFlow<PlayingMediaInfo?>(replay = 1)
    private val _isPlaying = MutableSharedFlow<Boolean>(replay = 1)
    private val _duration = MutableSharedFlow<Long>(replay = 1)
    private val _progress = MutableSharedFlow<Long>(replay = 1)

    fun setPlayingMediaInfo(playingMediaInfo: PlayingMediaInfo) {
        _playingMediaInfo.tryEmit(playingMediaInfo)
    }

    override fun setPlaylistAndPlay(playlist: List<Track>, startPlayingId: Long) {
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

    override val playingMediaInfo: SharedFlow<PlayingMediaInfo?>
        get() = _playingMediaInfo.asSharedFlow()

    override val isPlaying: SharedFlow<Boolean>
        get() = _isPlaying.asSharedFlow()

    override val duration: SharedFlow<Long>
        get() = _duration.asSharedFlow()

    override val progress: SharedFlow<Long>
        get() = _progress.asSharedFlow()
}
