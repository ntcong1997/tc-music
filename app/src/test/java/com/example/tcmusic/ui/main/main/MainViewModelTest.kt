package com.example.tcmusic.ui.main.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.usecase.player.*
import com.example.tcmusic.player.FakePlayer
import com.example.tcmusic.ui.main.host.HostViewModel
import com.example.test.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by TC on 19/01/2023.
 */

@ExperimentalCoroutinesApi
class MainViewModelTest {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var fakePlayer: FakePlayer

    private lateinit var viewModel: HostViewModel

    @Before
    fun setup() {
        fakePlayer = FakePlayer()

        viewModel = HostViewModel(
            observePlayingMediaUseCase = ObservePlayingMediaUseCase(
                fakePlayer,
                coroutineRule.testDispatcher
            ),
            observeIsPlayingUseCase = ObserveIsPlayingUseCase(fakePlayer, coroutineRule.testDispatcher),
            playUseCase = PlayUseCase(fakePlayer, coroutineRule.testDispatcher),
            pauseUseCase = PauseUseCase(fakePlayer, coroutineRule.testDispatcher),
            skipBackwardsUseCase = SkipBackwardsUseCase(fakePlayer, coroutineRule.testDispatcher),
            skipForwardUseCase = SkipForwardUseCase(fakePlayer, coroutineRule.testDispatcher)
        )
    }

    @Test
    fun `play media`() = runTest {
        viewModel.clickPlay()
        val isPlaying = viewModel.isPlaying.first()
        Assert.assertEquals(true, isPlaying)
    }

    @Test
    fun `pause media`() = runTest {
        viewModel.clickPause()
        val isPlaying = viewModel.isPlaying.first()
        Assert.assertEquals(false, isPlaying)
    }
}
