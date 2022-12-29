package com.example.tcmusic.ui.trackdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.usecase.player.*
import com.example.domain.usecase.track.GetTrackDetailUseCase
import com.example.tcmusic.player.FakePlayer
import com.example.tcmusic.repository.FakeTrackRepository
import com.example.tcmusic.ui.main.trackdetail.TrackDetailViewModel
import com.example.test.MainCoroutineRule
import com.example.test.data.PlayingMediaInfo
import com.example.test.data.Track_1
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by TC on 29/12/2022.
 */

@ExperimentalCoroutinesApi
class TrackDetailViewModelTest {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var fakePlayer: FakePlayer

    private lateinit var fakeTrackRepository: FakeTrackRepository

    @Before
    fun setup() {
        fakePlayer = FakePlayer()
        fakeTrackRepository = FakeTrackRepository()
    }

    @Test
    fun trackIsLoaded() = runTest {
        val viewModel = TrackDetailViewModel(
            observePlayingMediaInfoUseCase = ObservePlayingMediaInfoUseCase(fakePlayer, coroutineRule.testDispatcher),
            getTrackDetailUseCase = GetTrackDetailUseCase(fakeTrackRepository, coroutineRule.testDispatcher),
            observeDurationUseCase = ObserveDurationUseCase(fakePlayer, coroutineRule.testDispatcher),
            observeProgressUseCase = ObserveProgressUseCase(fakePlayer, coroutineRule.testDispatcher),
            observeIsPlayingUseCase = ObserveIsPlayingUseCase(fakePlayer, coroutineRule.testDispatcher),
            playUseCase = PlayUseCase(fakePlayer, coroutineRule.testDispatcher),
            pauseUseCase = PauseUseCase(fakePlayer, coroutineRule.testDispatcher),
            skipBackwardsUseCase = SkipBackwardsUseCase(fakePlayer, coroutineRule.testDispatcher),
            skipForwardUseCase = SkipForwardUseCase(fakePlayer, coroutineRule.testDispatcher),
            seekToUseCase = SeekToUseCase(fakePlayer, coroutineRule.testDispatcher)
        )

        fakePlayer.setPlayingMediaInfo(PlayingMediaInfo)
        val track = viewModel.track.first()
        assertThat(track, equalTo(Track_1))
    }
}
