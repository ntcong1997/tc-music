package com.example.tcmusic.core.domain.usecase.track

import com.example.tcmusic.core.common.network.Dispatcher
import com.example.tcmusic.core.common.network.TcMusicDispatchers
import com.example.tcmusic.core.data.repository.TrackRepository
import com.example.tcmusic.core.domain.usecase.CoroutineUseCase
import com.example.tcmusic.core.model.Track
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by TC on 01/12/2022.
 */

class GetTrackDetailUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
    @Dispatcher(TcMusicDispatchers.IO) dispatcher: CoroutineDispatcher
) : com.example.tcmusic.core.domain.usecase.CoroutineUseCase<GetTrackDetailParams, Track?>(dispatcher) {
    override suspend fun execute(parameters: GetTrackDetailParams): Track? {
        return trackRepository.getTrackDetail(parameters.trackId, parameters.trackVersion)
    }
}

data class GetTrackDetailParams(
    val trackId: String?,
    val trackVersion: String?
)
