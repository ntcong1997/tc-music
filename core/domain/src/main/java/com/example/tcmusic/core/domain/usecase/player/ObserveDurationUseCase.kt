package com.example.tcmusic.core.domain.usecase.player

import com.example.tcmusic.core.common.network.Dispatcher
import com.example.tcmusic.core.common.network.TcMusicDispatchers
import com.example.tcmusic.core.domain.usecase.FlowUseCase
import com.example.tcmusic.core.player.Player
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 14/12/2022.
 */

class ObserveDurationUseCase @Inject constructor(
    private val player: Player,
    @Dispatcher(TcMusicDispatchers.IO) dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Long>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Long> {
        return player.duration
    }
}
