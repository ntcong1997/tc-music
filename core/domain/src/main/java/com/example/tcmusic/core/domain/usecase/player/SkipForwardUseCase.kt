package com.example.tcmusic.core.domain.usecase.player

import com.example.tcmusic.core.player.Player
import javax.inject.Inject

/**
 * Created by TC on 14/12/2022.
 */

class SkipForwardUseCase @Inject constructor(
    private val player: Player
) {
    operator fun invoke() {
        player.skipForward()
    }
}
