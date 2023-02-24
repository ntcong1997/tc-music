package com.example.tcmusic.core.domain.usecase.artist

import androidx.paging.PagingData
import com.example.tcmusic.core.common.network.Dispatcher
import com.example.tcmusic.core.common.network.TcMusicDispatchers
import com.example.tcmusic.core.data.repository.ArtistRepository
import com.example.tcmusic.core.domain.usecase.FlowUseCase
import com.example.tcmusic.core.model.Artist
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

/**
 * Created by TC on 05/01/2023.
 */
class SearchArtistsUseCase @Inject constructor(
    private val artistRepository: ArtistRepository,
    @Dispatcher(TcMusicDispatchers.IO) dispatcher: CoroutineDispatcher
) : FlowUseCase<String?, PagingData<Artist>>(dispatcher) {
    override fun execute(parameters: String?): Flow<PagingData<Artist>> {
        return artistRepository.searchArtists(parameters)
    }
}
