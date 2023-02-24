package com.example.tcmusic.feature.search

import com.example.tcmusic.core.common.util.collectData
import com.example.tcmusic.core.domain.usecase.artist.SearchArtistsUseCase
import com.example.tcmusic.core.domain.usecase.player.SetPlaylistAndPlayUseCase
import com.example.tcmusic.core.domain.usecase.track.SearchTracksUseCase
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.testing.player.TestPlayer
import com.example.tcmusic.core.testing.repository.TestArtistRepository
import com.example.tcmusic.core.testing.repository.TestTrackRepository
import com.example.tcmusic.core.testing.util.MainDispatcherRule
import kotlin.test.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by TC on 24/02/2023.
 */
class SearchViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testTrackRepository = TestTrackRepository()

    private val testArtistRepository = TestArtistRepository()

    private val testPlayer = TestPlayer()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        viewModel = SearchViewModel(
            SearchTracksUseCase(testTrackRepository, mainDispatcherRule.testDispatcher),
            SearchArtistsUseCase(testArtistRepository, mainDispatcherRule.testDispatcher),
            SetPlaylistAndPlayUseCase(testPlayer)
        )
    }

    @Test
    fun `search tracks`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.tracks.collect() }

        viewModel.search("a")
        assertEquals(
            listOf(),
            viewModel.tracks.value.collectData(mainDispatcherRule.testDispatcher)
        )
        viewModel.search("ct")
        delay(500L)
        assertEquals(
            listOf(
                Track(
                    id = "10",
                    image = null,
                    genre = "Pop",
                    lyrics = listOf(),
                    title = "Perfect",
                    subTitle = "Ed Sheeran",
                    uri = "https://...",
                    version = null
                )
            ),
            viewModel.tracks.value.collectData(mainDispatcherRule.testDispatcher)
        )
        viewModel.search("a")
        assertEquals(
            listOf(),
            viewModel.tracks.value.collectData(mainDispatcherRule.testDispatcher)
        )

        collectJob.cancel()
    }

    @Test
    fun `search artists`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.artists.collect() }

        viewModel.search("a")
        assertEquals(
            listOf(),
            viewModel.artists.value.collectData(mainDispatcherRule.testDispatcher)
        )
        viewModel.search("ct")
        delay(500L)
        assertEquals(
            listOf(
                Artist(
                    avatar = null,
                    id = "2",
                    name = "One Direction",
                    topSongs = listOf()
                )
            ),
            viewModel.artists.value.collectData(mainDispatcherRule.testDispatcher)
        )
        viewModel.search("a")
        assertEquals(
            listOf(),
            viewModel.artists.value.collectData(mainDispatcherRule.testDispatcher)
        )

        collectJob.cancel()
    }
}
