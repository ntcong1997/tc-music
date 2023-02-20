package com.example.tcmusic.core.domain.usecase.artist

import com.example.tcmusic.core.common.network.Dispatcher
import com.example.tcmusic.core.common.network.TcMusicDispatchers
import com.example.tcmusic.core.data.repository.ArtistRepository
import com.example.tcmusic.core.domain.usecase.CoroutineUseCase
import com.example.tcmusic.core.model.Artist
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by TC on 09/01/2023.
 */

class GetArtistDetailUseCase @Inject constructor(
    private val artistRepository: ArtistRepository,
    @Dispatcher(TcMusicDispatchers.IO) dispatcher: CoroutineDispatcher
) : CoroutineUseCase<String?, Artist?>(dispatcher) {
    override suspend fun execute(parameters: String?): Artist? {
        return artistRepository.getArtistDetail(parameters)
    }
}
