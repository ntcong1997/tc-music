package com.example.tcmusic.core.domain.usecase.player

import com.example.tcmusic.core.common.network.Dispatcher
import com.example.tcmusic.core.common.network.TcMusicDispatchers
import com.example.tcmusic.core.domain.usecase.FlowUseCase
import com.example.tcmusic.core.model.PlayingMedia
import com.example.tcmusic.core.player.Player
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

/**
 * Created by TC on 14/12/2022.
 */

class ObservePlayingMediaUseCase @Inject constructor(
    private val player: Player,
    @Dispatcher(TcMusicDispatchers.IO) dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, PlayingMedia?>(dispatcher) {
    override fun execute(parameters: Unit): Flow<PlayingMedia?> {
        return player.playingMedia
    }
}
