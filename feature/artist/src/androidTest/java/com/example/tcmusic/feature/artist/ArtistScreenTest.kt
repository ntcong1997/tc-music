package com.example.tcmusic.feature.artist

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.tcmusic.core.common.util.compactTo2Letters
import com.example.tcmusic.core.testing.data.artistTestData1
import org.junit.Rule
import org.junit.Test
import com.example.tcmusic.core.ui.R as uiR

/**
 * Created by TC on 28/02/2023.
 */

/**
 * UI test for checking the correct behaviour of the Artist screen;
 * Verifies that, when a specific UiState is set, the corresponding
 * composables and details are shown
 */
class ArtistScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun artistUiStateLoading_showLoading() {
        composeTestRule.setContent {
            ArtistScreen(
                artistUiState = ArtistUiState.Loading,
                onBackClick = {},
                onPlayClick = {},
                onTrackClick = {}
            )
        }

        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(uiR.string.loading_dialog_text_loading))
            .assertExists()
    }

    @Test
    fun artistUiStateSuccess_artist_imageNotNull_topSongsNotEmpty() {
        val artist = artistTestData1

        composeTestRule.setContent {
            ArtistScreen(
                artistUiState = ArtistUiState.Success(artist),
                onBackClick = {},
                onPlayClick = {},
                onTrackClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(ArtistAvatarContentDescription)
            .assertExists()

        composeTestRule
            .onNodeWithText(artist.name.compactTo2Letters())
            .assertDoesNotExist()

        composeTestRule
            .onAllNodesWithText(artist.name.orEmpty())
            .assertCountEquals(4)
    }
}