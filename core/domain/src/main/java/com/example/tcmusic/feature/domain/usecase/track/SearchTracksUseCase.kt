package com.example.tcmusic.feature.domain.usecase.track

import androidx.paging.PagingData
import com.example.tcmusic.feature.common.network.Dispatcher
import com.example.tcmusic.feature.common.network.TcMusicDispatchers
import com.example.tcmusic.feature.data.repository.TrackRepository
import com.example.tcmusic.feature.domain.usecase.FlowUseCase
import com.example.tcmusic.feature.model.Track
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by TC on 05/01/2023.
 */
class SearchTracksUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
    @Dispatcher(TcMusicDispatchers.IO) dispatcher: CoroutineDispatcher
) : FlowUseCase<String?, PagingData<Track>>(dispatcher) {
    override fun execute(parameters: String?): Flow<PagingData<Track>> {
        return trackRepository.searchTracks(parameters)
    }
}
