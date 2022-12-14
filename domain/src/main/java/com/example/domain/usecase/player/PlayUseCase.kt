package com.example.domain.usecase.player

import com.example.domain.CoroutineUseCase
import com.example.domain.di.IoDispatcher
import com.example.domain.player.Player
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by TC on 14/12/2022.
 */

class PlayUseCase @Inject constructor(
    private val player: Player,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Unit, Unit>(dispatcher) {
    override suspend fun execute(parameters: Unit) {
        player.play()
    }
}