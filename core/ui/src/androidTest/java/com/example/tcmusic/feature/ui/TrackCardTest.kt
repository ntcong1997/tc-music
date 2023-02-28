package com.example.tcmusic.feature.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.ui.TrackCard
import com.example.tcmusic.core.ui.util.TrackCompactTitleImageContentDescription
import com.example.tcmusic.core.ui.util.TrackImageContentDescription
import org.junit.Rule
import org.junit.Test

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
                onClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(TrackImageContentDescription)
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription(TrackCompactTitleImageContentDescription)
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
                onClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(TrackImageContentDescription)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithContentDescription(TrackCompactTitleImageContentDescription)
            .assertExists()

        composeTestRule
            .onNodeWithText("SL")
            .assertIsDisplayed()
    }
}
