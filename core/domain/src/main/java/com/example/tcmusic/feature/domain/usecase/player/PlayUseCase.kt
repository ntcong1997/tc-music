package com.example.tcmusic.feature.domain.usecase.player

import com.example.tcmusic.feature.player.Player
import javax.inject.Inject

/**
 * Created by TC on 14/12/2022.
 */

class PlayUseCase @Inject constructor(
    private val player: Player
) {
    operator fun invoke() {
        player.play()
    }
}
