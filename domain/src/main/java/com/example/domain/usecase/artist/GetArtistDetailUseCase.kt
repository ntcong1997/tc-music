package com.example.domain.usecase.artist

import com.example.domain.CoroutineUseCase
import com.example.domain.di.IoDispatcher
import com.example.domain.repository.ArtistRepository
import com.example.model.Artist
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by TC on 09/01/2023.
 */

class GetArtistDetailUseCase @Inject constructor(
    private val artistRepository: ArtistRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CoroutineUseCase<String?, Artist?>(dispatcher) {
    override suspend fun execute(parameters: String?): Artist? {
        return artistRepository.getArtistDetail(parameters)
    }
}
