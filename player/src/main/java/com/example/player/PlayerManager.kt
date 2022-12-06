package com.example.player

import com.example.domain.player.Player
import com.example.model.Track
import com.google.android.exoplayer2.ExoPlayer
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Created by TC on 05/12/2022.
 */

class PlayerManager @Inject constructor(
    private val player: ExoPlayer
) : Player {
    private val _trackPlaying = Channel<Track?>(Channel.CONFLATED)

    override val trackPlaying: Flow<Track?>
        get() = _trackPlaying.receiveAsFlow()

    override fun play(track: Track) {
    }

    override fun pause() {
    }
}
