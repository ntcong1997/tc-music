package com.example.domain.usecase.track

import androidx.paging.PagingData
import com.example.domain.FlowUseCase
import com.example.domain.Result
import com.example.domain.di.IoDispatcher
import com.example.domain.repository.TrackRepository
import com.example.model.Track
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by TC on 05/01/2023.
 */
class SearchTracksUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<String?, PagingData<Track>>(dispatcher) {
    override fun execute(parameters: String?): Flow<Result<PagingData<Track>>> {
        return trackRepository.searchTracks(parameters).map {
            Result.Success(it)
        }
    }
}
