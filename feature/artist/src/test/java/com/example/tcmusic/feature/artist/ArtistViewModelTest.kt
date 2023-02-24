package com.example.tcmusic.feature.artist

import androidx.lifecycle.SavedStateHandle
import com.example.tcmusic.core.domain.usecase.artist.GetArtistDetailUseCase
import com.example.tcmusic.core.domain.usecase.player.SetPlaylistAndPlayUseCase
import com.example.tcmusic.core.testing.data.artistTestData1
import com.example.tcmusic.core.testing.player.TestPlayer
import com.example.tcmusic.core.testing.repository.TestArtistRepository
import com.example.tcmusic.core.testing.util.MainDispatcherRule
import com.example.tcmusic.feature.artist.navigation.artistIdArg
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by TC on 23/02/2023.
 */

class ArtistViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testArtistRepository = TestArtistRepository()

    private val testPlayer = TestPlayer()

    private lateinit var viewModel: ArtistViewModel

    @Before
    fun setup() {
        viewModel = ArtistViewModel(
            savedStateHandle = SavedStateHandle(mapOf(artistIdArg to "1")),
            getArtistDetailUseCase = GetArtistDetailUseCase(
                testArtistRepository,
                mainDispatcherRule.testDispatcher
            ),
            setPlaylistAndPlayUseCase = SetPlaylistAndPlayUseCase(
                testPlayer
            )
        )
    }

    @Test
    fun `artistId matches artistId from SavedStateHandle`() {
        assertEquals("1", viewModel.artistId)
    }

    @Test
    fun `artistUiState success`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.artistUiState.collect() }

        assertEquals(ArtistUiState.Success(artistTestData1), viewModel.artistUiState.value)

        collectJob.cancel()
    }
}
