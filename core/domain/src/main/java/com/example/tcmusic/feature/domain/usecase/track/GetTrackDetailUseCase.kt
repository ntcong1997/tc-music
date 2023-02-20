package com.example.tcmusic.feature.domain.usecase.track

import com.example.tcmusic.feature.common.network.Dispatcher
import com.example.tcmusic.feature.common.network.TcMusicDispatchers
import com.example.tcmusic.feature.data.repository.TrackRepository
import com.example.tcmusic.feature.domain.usecase.CoroutineUseCase
import com.example.tcmusic.feature.model.Track
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by TC on 01/12/2022.
 */

class GetTrackDetailUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
    @Dispatcher(TcMusicDispatchers.IO) dispatcher: CoroutineDispatcher
) : CoroutineUseCase<GetTrackDetailParams, Track?>(dispatcher) {
    override suspend fun execute(parameters: GetTrackDetailParams): Track? {
        return trackRepository.getTrackDetail(parameters.trackId, parameters.trackVersion)
    }
}

data class GetTrackDetailParams(
    val trackId: String?,
    val trackVersion: String?
)
