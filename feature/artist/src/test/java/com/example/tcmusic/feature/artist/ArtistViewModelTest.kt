package com.example.tcmusic.feature.artist

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.tcmusic.core.domain.usecase.artist.GetArtistDetailUseCase
import com.example.tcmusic.core.domain.usecase.player.*
import com.example.tcmusic.core.testing.data.artistTestData1
import com.example.tcmusic.core.testing.data.artistsTestData
import com.example.tcmusic.core.testing.player.TestPlayer
import com.example.tcmusic.core.testing.util.MainDispatcherRule
import com.example.tcmusic.feature.artist.navigation.artistIdArg
import com.example.tcmusic.core.testing.repository.TestArtistRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

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
            observePlayingMediaUseCase = ObservePlayingMediaUseCase(
                testPlayer,
                mainDispatcherRule.testDispatcher
            ),
            observeIsPlayingUseCase = ObserveIsPlayingUseCase(
                testPlayer,
                mainDispatcherRule.testDispatcher
            ),
            getArtistDetailUseCase = GetArtistDetailUseCase(
                testArtistRepository,
                mainDispatcherRule.testDispatcher
            ),
            setPlaylistAndPlayUseCase = SetPlaylistAndPlayUseCase(
                testPlayer
            ),
            playUseCase = PlayUseCase(testPlayer),
            pauseUseCase = PauseUseCase(testPlayer),
            skipBackwardsUseCase = SkipBackwardsUseCase(testPlayer),
            skipForwardUseCase = SkipForwardUseCase(testPlayer)
        )
    }

    @Test
    fun `get artist success`() = runTest {
        viewModel.artistUiState.test {
            assertEquals(ArtistUiState.Loading, awaitItem())
            assertEquals(ArtistUiState.Success(artistTestData1), awaitItem())
        }
    }

//    @Test
//    fun `play media`() = runTest {
//        viewModel.clickPlayingMediaPlay()
//        val isPlaying = viewModel.isPlaying.first()
//        assertEquals(true, isPlaying)
//    }
//
//    @Test
//    fun `pause media`() = runTest {
//        viewModel.clickPlayingMediaPause()
//        val isPlaying = viewModel.isPlaying.first()
//        assertEquals(false, isPlaying)
//    }
}