package com.example.tcmusic.core.domain.usecase.track

import androidx.paging.PagingData
import com.example.tcmusic.core.common.network.Dispatcher
import com.example.tcmusic.core.common.network.TcMusicDispatchers
import com.example.tcmusic.core.data.repository.TrackRepository
import com.example.tcmusic.core.domain.usecase.FlowUseCase
import com.example.tcmusic.core.model.Track
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

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
