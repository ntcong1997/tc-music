package com.example.domain.player

import com.example.model.PlayingMediaInfo
import com.example.model.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

/**
 * Created by TC on 05/12/2022.
 */

interface Player {
    suspend fun setPlaylistAndPlay(playlist: List<Track>, startPlayingId: Long)

    fun play()

    fun pause()

    fun skipBackwards()

    fun skipForward()

    fun seekTo(position: Long)

    val playingMediaInfo: SharedFlow<PlayingMediaInfo?>

    val isPlaying: SharedFlow<Boolean>

    val duration: SharedFlow<Long>

    val progress: SharedFlow<Long>
}
