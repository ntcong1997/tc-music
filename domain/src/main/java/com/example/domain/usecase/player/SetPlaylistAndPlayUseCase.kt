package com.example.domain.usecase.player

import com.example.domain.CoroutineUseCase
import com.example.domain.di.IoDispatcher
import com.example.domain.player.Player
import com.example.model.Track
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by TC on 14/12/2022.
 */

class SetPlaylistAndPlayUseCase @Inject constructor(
    private val player: Player,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<SetPlaylistAndPlayParams, Unit>(dispatcher) {
    override suspend fun execute(parameters: SetPlaylistAndPlayParams) {
        player.setPlaylistAndPlay(parameters.playlist, parameters.startPlayingId)
    }
}

data class SetPlaylistAndPlayParams(
    val playlist: List<Track>,
    val startPlayingId: Long
)