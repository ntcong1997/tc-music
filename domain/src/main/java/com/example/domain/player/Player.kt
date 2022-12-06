package com.example.domain.player

import com.example.model.Track
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 05/12/2022.
 */

interface Player {
    val trackPlaying: Flow<Track?>

    fun play(track: Track)

    fun pause()
}
