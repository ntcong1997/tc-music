package com.example.domain.usecase.artist

import androidx.paging.PagingData
import com.example.domain.FlowUseCase
import com.example.domain.Result
import com.example.domain.di.IoDispatcher
import com.example.domain.repository.ArtistRepository
import com.example.model.Artist
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by TC on 05/01/2023.
 */
class SearchArtistsUseCase @Inject constructor(
    private val artistRepository: ArtistRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<String?, PagingData<Artist>>(dispatcher) {
    override fun execute(parameters: String?): Flow<Result<PagingData<Artist>>> {
        return artistRepository.searchArtists(parameters).map {
            Result.Success(it)
        }
    }
}