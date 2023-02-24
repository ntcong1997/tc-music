package com.example.tcmusic.feature.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.ui.ArtistCard
import com.example.tcmusic.core.ui.util.ArtistAvatarCompactNameContentDescription
import com.example.tcmusic.core.ui.util.ArtistAvatarContentDescription
import org.junit.Rule
import org.junit.Test

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
                onClick = {}
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
                onClick = {}
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
}
