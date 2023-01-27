package com.example.domain.usecase.track

import com.example.domain.CoroutineUseCase
import com.example.domain.di.IoDispatcher
import com.example.domain.repository.TrackRepository
import com.example.model.Track
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by TC on 01/12/2022.
 */

class GetTrackDetailUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<GetTrackDetailParams, Track?>(dispatcher) {
    override suspend fun execute(parameters: GetTrackDetailParams): Track? {
        return trackRepository.getTrackDetail(parameters.trackId, parameters.trackVersion)
    }
}

data class GetTrackDetailParams(
    val trackId: String?,
    val trackVersion: String?
)
