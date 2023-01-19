package com.example.tcmusic.ui.main.trackdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.usecase.player.*
import com.example.domain.usecase.track.GetTrackDetailUseCase
import com.example.tcmusic.player.FakePlayer
import com.example.tcmusic.repository.FakeTrackRepository
import com.example.tcmusic.ui.main.trackdetail.TrackDetailViewModel
import com.example.test.MainCoroutineRule
import com.example.test.data.Track_1
import com.example.test.data.Tracks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
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

    private lateinit var viewModel: TrackDetailViewModel

    @Before
    fun setup() {
        fakePlayer = FakePlayer()
        val fakeTrackRepository = FakeTrackRepository()

        viewModel = TrackDetailViewModel(
            observePlayingMediaInfoUseCase = ObservePlayingMediaInfoUseCase(
                fakePlayer,
                coroutineRule.testDispatcher
            ),
            getTrackDetailUseCase = GetTrackDetailUseCase(
                fakeTrackRepository,
                coroutineRule.testDispatcher
            ),
            observeDurationUseCase = ObserveDurationUseCase(
                fakePlayer,
                coroutineRule.testDispatcher
            ),
            observeProgressUseCase = ObserveProgressUseCase(
                fakePlayer,
                coroutineRule.testDispatcher
            ),
            observeIsPlayingUseCase = ObserveIsPlayingUseCase(
                fakePlayer,
                coroutineRule.testDispatcher
            ),
            playUseCase = PlayUseCase(fakePlayer, coroutineRule.testDispatcher),
            pauseUseCase = PauseUseCase(fakePlayer, coroutineRule.testDispatcher),
            skipBackwardsUseCase = SkipBackwardsUseCase(fakePlayer, coroutineRule.testDispatcher),
            skipForwardUseCase = SkipForwardUseCase(fakePlayer, coroutineRule.testDispatcher),
            seekToUseCase = SeekToUseCase(fakePlayer, coroutineRule.testDispatcher)
        )
    }

    @Test
    fun `get track detail from playing media`() = runTest {
        fakePlayer.setPlaylistAndPlay(Tracks, Track_1.key?.toLong() ?: 0L)
        val track = viewModel.track.first()
        assertThat(track, equalTo(Track_1))
    }

    @Test
    fun `play media`() = runTest {
        viewModel.clickPlay()
        val isPlaying = viewModel.trackIsPlaying.first()
        assertEquals(true, isPlaying)
    }

    @Test
    fun `pause media`() = runTest {
        viewModel.clickPause()
        val isPlaying = viewModel.trackIsPlaying.first()
        assertEquals(false, isPlaying)
    }
}
