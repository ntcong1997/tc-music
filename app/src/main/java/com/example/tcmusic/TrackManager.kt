package com.example.tcmusic

import com.example.model.Track
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/**
 * Created by TC on 01/12/2022.
 */

interface TrackManager {
    val trackPlaying: Flow<Track>

    fun setTrackPlaying(track: Track)
}

class TrackManagerImpl @Inject constructor(

) : TrackManager {
    private val _trackPlaying = Channel<Track>(Channel.CONFLATED)

    override val trackPlaying: Flow<Track>
        get() = _trackPlaying.receiveAsFlow()

    override fun setTrackPlaying(track: Track) {
        _trackPlaying.trySend(track)
    }

}