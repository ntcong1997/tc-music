package com.example.domain.usecase.player

import com.example.domain.FlowUseCase
import com.example.domain.Result
import com.example.domain.di.IoDispatcher
import com.example.domain.player.Player
import com.example.model.PlayingMediaInfo
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

/**
 * Created by TC on 14/12/2022.
 */

class ObservePlayingMediaInfoUseCase @Inject constructor(
    private val player: Player,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, PlayingMediaInfo?>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Result<PlayingMediaInfo?>> {
        return player.playingMediaInfo.map {
            Result.Success(it)
        }
    }
}
