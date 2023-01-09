package com.example.tcmusic.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.*
import app.cash.turbine.test
import com.example.domain.usecase.artist.SearchArtistsUseCase
import com.example.domain.usecase.track.SearchTracksUseCase
import com.example.model.Artist
import com.example.model.Track
import com.example.tcmusic.repository.FakeArtistRepository
import com.example.tcmusic.repository.FakeTrackRepository
import com.example.tcmusic.ui.main.main.search.SearchViewModel
import com.example.tcmusic.util.collectData
import com.example.test.MainCoroutineRule
import com.example.test.data.Artists
import com.example.test.data.Tracks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by TC on 08/01/2023.
 */
@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModelTest {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        val fakeTrackRepository = FakeTrackRepository()
        val fakeArtistRepository = FakeArtistRepository()

        viewModel = SearchViewModel(
            SearchTracksUseCase(fakeTrackRepository, coroutineRule.testDispatcher),
            SearchArtistsUseCase(fakeArtistRepository, coroutineRule.testDispatcher)
        )
    }

    @Test
    fun `search tracks with less than 2 characters`() = runTest {
        viewModel.search("a")
        viewModel.tracks.test {
            assertEquals(
                listOf<Track>(),
                awaitItem().collectData(coroutineRule.testDispatcher)
            )
        }
    }

    @Test
    fun `search tracks with more than 1 character`() = runTest {
        viewModel.search("ll")
        viewModel.tracks.test {
            assertEquals(
                Tracks.filter { it.title?.contains("ll", ignoreCase = true) == true },
                awaitItem().collectData(coroutineRule.testDispatcher)
            )
        }
    }

    @Test
    fun `search artists with less than 2 characters`() = runTest {
        viewModel.search("a")
        viewModel.artists.test {
            assertEquals(
                listOf<Artist>(),
                awaitItem().collectData(coroutineRule.testDispatcher)
            )
        }
    }

    @Test
    fun `search artists with more than 1 character`() = runTest {
        viewModel.search("le")
        viewModel.artists.test {
            assertEquals(
                Artists.filter { it.artistName?.contains("le", ignoreCase = true) == true },
                awaitItem().collectData(coroutineRule.testDispatcher)
            )
        }
    }
}