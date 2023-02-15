package com.example.tcmusic.core.domain.usecase.player

import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.player.Player
import javax.inject.Inject

/**
 * Created by TC on 14/12/2022.
 */

class SetPlaylistAndPlayUseCase @Inject constructor(
    private val player: Player
) {
    operator fun invoke(parameters: SetPlaylistAndPlayParams) {
        player.setPlaylistAndPlay(parameters.playlist, parameters.startPlayingId)
    }
}

data class SetPlaylistAndPlayParams(
    val playlist: List<Track>,
    val startPlayingId: Long
)
