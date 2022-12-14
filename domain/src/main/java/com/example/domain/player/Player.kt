package com.example.domain.player

import com.example.model.PlayingMediaInfo
import com.example.model.Track
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 05/12/2022.
 */

interface Player {
    suspend fun setPlaylistAndPlay(playlist: List<Track>, startPlayingId: Long)
    
    fun play()
    
    fun pause()

    val playingMediaInfo: Flow<PlayingMediaInfo?>

    val isPlaying: Flow<Boolean>
}
