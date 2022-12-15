package com.example.domain.usecase.player

import com.example.domain.FlowUseCase
import com.example.domain.Result
import com.example.domain.di.IoDispatcher
import com.example.domain.player.Player
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by TC on 14/12/2022.
 */

class ObserveDurationUseCase @Inject constructor(
    private val player: Player,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Long>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Result<Long>> {
        return player.duration.map {
            Result.Success(it)
        }
    }
}
