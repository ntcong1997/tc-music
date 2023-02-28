package com.example.tcmusic.core.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.tcmusic.core.model.Track
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by TC on 24/02/2023.
 */
class TrackCardTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testTrackImage_withImageNotNull() {
        val track = Track(
            id = "1",
            image = "https://upload.wikimedia.org/wikipedia/en/7/7d/One_Direction_-_Story_of_My_Life_%28Official_Single_Cover%29.png",
            genre = "Pop",
            lyrics = listOf(),
            title = "Story of my life",
            subTitle = "One Direction",
            uri = "https://...",
            version = null
        )

        composeTestRule.setContent {
            TrackCard(
                track = track,
                onClick = {},
                onMoreClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription("$TrackImageContentDescriptionPrefix-1")
            .assertExists()

        composeTestRule
            .onNodeWithText("SL")
            .assertDoesNotExist()
    }

    @Test
    fun testTrackImage_withImageNull() {
        val track = Track(
            id = "1",
            image = null,
            genre = "Pop",
            lyrics = listOf(),
            title = "Story of my life",
            subTitle = "One Direction",
            uri = "https://...",
            version = null
        )

        composeTestRule.setContent {
            TrackCard(
                track = track,
                onClick = {},
                onMoreClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription("$TrackImageContentDescriptionPrefix-1")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("SL")
            .assertExists()
    }

    @Test
    fun testTrack_performClick() {
        var onClickCalled = false
        var onMoreClickCalled = false

        val track = Track(
            id = "1",
            image = null,
            genre = "Pop",
            lyrics = listOf(),
            title = "Story of my life",
            subTitle = "One Direction",
            uri = "https://...",
            version = null
        )

        composeTestRule.setContent {
            TrackCard(
                track = track,
                onClick = {
                    onClickCalled = true
                },
                onMoreClick = {
                    onMoreClickCalled = true
                }
            )
        }

        composeTestRule
            .onNodeWithContentDescription("$TrackCardContentDescriptionPrefix-1")
            .performClick()

        assertTrue(onClickCalled)

        composeTestRule
            .onNodeWithContentDescription("$TrackMoreIconContentDescriptionPrefix-1")
            .performClick()

        assertTrue(onMoreClickCalled)
    }
}
