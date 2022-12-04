package com.example.domain.usecase.track

import com.example.domain.CoroutineUseCase
import com.example.domain.di.IoDispatcher
import com.example.domain.model.Track
import com.example.domain.repository.TrackRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by TC on 01/12/2022.
 */

class GetTrackDetailUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<String?, Track?>(dispatcher) {
    override suspend fun execute(parameters: String?): Track? {
        return trackRepository.getTrackDetail(parameters)
    }
}