package com.example.tcmusic.feature.domain.usecase.artist

import androidx.paging.PagingData
import com.example.tcmusic.feature.common.network.Dispatcher
import com.example.tcmusic.feature.common.network.TcMusicDispatchers
import com.example.tcmusic.feature.data.repository.ArtistRepository
import com.example.tcmusic.feature.domain.usecase.FlowUseCase
import com.example.tcmusic.feature.model.Artist
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

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
