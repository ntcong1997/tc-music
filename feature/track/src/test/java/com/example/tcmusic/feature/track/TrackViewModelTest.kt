package com.example.tcmusic.feature.track

import androidx.lifecycle.SavedStateHandle
import com.example.tcmusic.core.domain.usecase.player.*
import com.example.tcmusic.core.domain.usecase.track.GetTrackDetailUseCase
import com.example.tcmusic.core.testing.data.trackTestData1
import com.example.tcmusic.core.testing.data.trackTestData2
import com.example.tcmusic.core.testing.player.TestPlayer
import com.example.tcmusic.core.testing.repository.TestTrackRepository
import com.example.tcmusic.core.testing.util.MainDispatcherRule
import com.example.tcmusic.feature.track.navigation.trackIdArg
import com.example.tcmusic.feature.track.navigation.trackVersionArg
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
class TrackViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testTrackRepository = TestTrackRepository()

    private val testPlayer = TestPlayer()

    private lateinit var viewModel: TrackViewModel

    @Before
    fun setup() {
        viewModel = TrackViewModel(
            savedStateHandle = SavedStateHandle(mapOf(trackIdArg to "1", trackVersionArg to "1")),
            observePlayingMediaUseCase = ObservePlayingMediaUseCase(
                testPlayer,
                mainDispatcherRule.testDispatcher
            ),
            getTrackDetailUseCase = GetTrackDetailUseCase(
                testTrackRepository,
                mainDispatcherRule.testDispatcher
            ),
            observeDurationUseCase = ObserveDurationUseCase(
                testPlayer,
                mainDispatcherRule.testDispatcher
            ),
            observeProgressUseCase = ObserveProgressUseCase(
                testPlayer,
                mainDispatcherRule.testDispatcher
            ),
            observeIsPlayingUseCase = ObserveIsPlayingUseCase(
                testPlayer,
                mainDispatcherRule.testDispatcher
            ),
            playUseCase = PlayUseCase(testPlayer),
            pauseUseCase = PauseUseCase(testPlayer),
            skipBackwardsUseCase = SkipBackwardsUseCase(testPlayer),
            skipForwardUseCase = SkipForwardUseCase(testPlayer),
            seekToUseCase = SeekToUseCase(testPlayer)
        )
    }

    @Test
    fun `trackId and trackVersion match trackId and trackVersion from SavedStateHandle`() {
        assertEquals("1", viewModel.trackId)
        assertEquals("1", viewModel.trackVersion)
    }

    @Test
    fun `trackUiState success of trackId from SavedStateHandle`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.trackUiState.collect() }

        assertEquals(TrackUiState.Success(trackTestData1), viewModel.trackUiState.value)

        collectJob.cancel()
    }

    @Test
    fun `trackUiState success of trackId from TestPlayer`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.trackUiState.collect() }

        testPlayer.setPlaylistAndPlay(listOf(trackTestData2), trackTestData2.id?.toLong() ?: 0L)
        assertEquals(TrackUiState.Success(trackTestData2), viewModel.trackUiState.value)

        collectJob.cancel()
    }

    @Test
    fun trackDuration() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.trackDuration.collect() }

        assertEquals(0L, viewModel.trackDuration.value)

        collectJob.cancel()
    }

    @Test
    fun `trackProgress seekTo`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.trackProgress.collect() }

        assertEquals(0L, viewModel.trackProgress.value)
        testPlayer.seekTo(1L)
        assertEquals(1L, viewModel.trackProgress.value)

        collectJob.cancel()
    }

    @Test
    fun `trackIsPlaying play and pause`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.trackIsPlaying.collect() }

        assertEquals(true, viewModel.trackIsPlaying.value)
        testPlayer.pause()
        assertEquals(false, viewModel.trackIsPlaying.value)

        collectJob.cancel()
    }
}
