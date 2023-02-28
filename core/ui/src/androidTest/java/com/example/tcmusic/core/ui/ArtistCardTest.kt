package com.example.tcmusic.core.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.ui.ArtistAvatarContentDescriptionPrefix
import com.example.tcmusic.core.ui.ArtistCard
import com.example.tcmusic.core.ui.ArtistCardContentDescriptionPrefix
import com.example.tcmusic.core.ui.ArtistMoreIconContentDescriptionPrefix
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
            id = "1",
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
            .onNodeWithContentDescription("$ArtistAvatarContentDescriptionPrefix-1")
            .assertExists()

        composeTestRule
            .onNodeWithText("OD")
            .assertDoesNotExist()
    }

    @Test
    fun testArtistAvatar_withAvatarIsNull() {
        val artist = Artist(
            avatar = null,
            id = "1",
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
            .onNodeWithContentDescription("$ArtistAvatarContentDescriptionPrefix-1")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("OD")
            .assertExists()
    }

    @Test
    fun testArtist_performClick() {
        var onClickCalled = false
        var onMoreClickCalled = false

        val artist = Artist(
            avatar = "https://cdn.britannica.com/30/164030-050-255C7C8E/One-Direction-Niall-Horan-Zayn-Malik-Liam-2011.jpg",
            id = "1",
            name = "One Direction",
            topSongs = listOf()
        )

        composeTestRule.setContent {
            ArtistCard(
                artist = artist,
                onClick = {
                    onClickCalled = true
                },
                onMoreClick = {
                    onMoreClickCalled = true
                }
            )
        }

        composeTestRule
            .onNodeWithContentDescription("$ArtistCardContentDescriptionPrefix-1")
            .performClick()

        assertTrue(onClickCalled)

        composeTestRule
            .onNodeWithContentDescription("$ArtistMoreIconContentDescriptionPrefix-1")
            .performClick()

        assertTrue(onMoreClickCalled)
    }
}
