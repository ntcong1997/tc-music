package com.example.tcmusic.feature.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.ui.ArtistCard
import com.example.tcmusic.core.ui.util.ArtistAvatarCompactNameContentDescription
import com.example.tcmusic.core.ui.util.ArtistAvatarContentDescription
import com.example.tcmusic.core.ui.util.ArtistCardContentDescription
import com.example.tcmusic.core.ui.util.ArtistMoreIconContentDescription
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by TC on 24/02/2023.
 */

class ArtistCardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testArtistAvatar_withAvatarIsNotNull() {
        val artist = Artist(
            avatar = "https://cdn.britannica.com/30/164030-050-255C7C8E/One-Direction-Niall-Horan-Zayn-Malik-Liam-2011.jpg",
            id = "2",
            name = "One Direction",
            topSongs = listOf()
        )

        composeTestRule.setContent {
            ArtistCard(
                artist = artist,
                onClick = {},
                onMoreClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(ArtistAvatarContentDescription)
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription(ArtistAvatarCompactNameContentDescription)
            .assertDoesNotExist()
    }

    @Test
    fun testArtistAvatar_withAvatarIsNull() {
        val artist = Artist(
            avatar = null,
            id = "2",
            name = "One Direction",
            topSongs = listOf()
        )

        composeTestRule.setContent {
            ArtistCard(
                artist = artist,
                onClick = {},
                onMoreClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(ArtistAvatarContentDescription)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithContentDescription(ArtistAvatarCompactNameContentDescription)
            .assertExists()

        composeTestRule
            .onNodeWithText("OD")
            .assertIsDisplayed()
    }

    @Test
    fun testArtist_performClick() {
        var onClickCalled = false

        val artist = Artist(
            avatar = "https://cdn.britannica.com/30/164030-050-255C7C8E/One-Direction-Niall-Horan-Zayn-Malik-Liam-2011.jpg",
            id = "2",
            name = "One Direction",
            topSongs = listOf()
        )

        composeTestRule.setContent {
            ArtistCard(
                artist = artist,
                onClick = {
                    onClickCalled = true
                },
                onMoreClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(ArtistCardContentDescription)
            .performClick()

        assertTrue(onClickCalled)
    }

    @Test
    fun testArtist_performMoreClick() {
        var onMoreClickCalled = false

        val artist = Artist(
            avatar = "https://cdn.britannica.com/30/164030-050-255C7C8E/One-Direction-Niall-Horan-Zayn-Malik-Liam-2011.jpg",
            id = "2",
            name = "One Direction",
            topSongs = listOf()
        )

        composeTestRule.setContent {
            ArtistCard(
                artist = artist,
                onClick = {},
                onMoreClick = {
                    onMoreClickCalled = true
                }
            )
        }

        composeTestRule
            .onNodeWithContentDescription(ArtistMoreIconContentDescription)
            .performClick()

        assertTrue(onMoreClickCalled)
    }
}
