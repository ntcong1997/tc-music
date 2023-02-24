package com.example.tcmusic.feature.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.tcmusic.core.testing.data.trackTestData1
import com.example.tcmusic.core.testing.data.trackTestData2
import com.example.tcmusic.core.ui.TrackCard
import com.example.tcmusic.core.ui.util.TrackCompactTitleContentDescription
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
        val track = trackTestData1

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
            .onNodeWithContentDescription(TrackCompactTitleContentDescription)
            .assertDoesNotExist()
    }

    @Test
    fun testTrackImage_withImageNull() {
        val track = trackTestData2

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
            .onNodeWithContentDescription(TrackCompactTitleContentDescription)
            .assertExists()
    }
}