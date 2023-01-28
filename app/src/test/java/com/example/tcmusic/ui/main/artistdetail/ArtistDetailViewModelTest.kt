package com.example.tcmusic.ui.main.artistdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.domain.usecase.artist.GetArtistDetailUseCase
import com.example.domain.usecase.player.*
import com.example.tcmusic.player.FakePlayer
import com.example.tcmusic.repository.FakeArtistRepository
import com.example.test.MainCoroutineRule
import com.example.test.data.Artist_1
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by TC on 19/01/2023.
 */

@ExperimentalCoroutinesApi
class ArtistDetailViewModelTest {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var fakePlayer: FakePlayer

    private lateinit var viewModel: ArtistDetailViewModel

    @Before
    fun setup() {
        fakePlayer = FakePlayer()
        val fakeArtistRepository = FakeArtistRepository()

        viewModel = ArtistDetailViewModel(
            observePlayingMediaInfoUseCase = ObservePlayingMediaInfoUseCase(
                fakePlayer,
                coroutineRule.testDispatcher
            ),
            observeIsPlayingUseCase = ObserveIsPlayingUseCase(
                fakePlayer,
                coroutineRule.testDispatcher
            ),
            getArtistDetailUseCase = GetArtistDetailUseCase(
                fakeArtistRepository,
                coroutineRule.testDispatcher
            ),
            setPlaylistAndPlayUseCase = SetPlaylistAndPlayUseCase(
                fakePlayer,
                coroutineRule.testDispatcher
            ),
            playUseCase = PlayUseCase(fakePlayer, coroutineRule.testDispatcher),
            pauseUseCase = PauseUseCase(fakePlayer, coroutineRule.testDispatcher),
            skipBackwardsUseCase = SkipBackwardsUseCase(fakePlayer, coroutineRule.testDispatcher),
            skipForwardUseCase = SkipForwardUseCase(fakePlayer, coroutineRule.testDispatcher)
        )
    }

    @Test
    fun `get artist detail success`() = runTest {
        viewModel.getArtistDetail(Artist_1.id)
        viewModel.artist.test {
            assertEquals(awaitItem(), Artist_1)
        }
    }

    @Test
    fun `play media`() = runTest {
        viewModel.clickPlayingMediaInfoPlay()
        val isPlaying = viewModel.isPlaying.first()
        assertEquals(true, isPlaying)
    }

    @Test
    fun `pause media`() = runTest {
        viewModel.clickPlayingMediaInfoPause()
        val isPlaying = viewModel.isPlaying.first()
        assertEquals(false, isPlaying)
    }
}
