package com.example.tcmusic.feature.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.tcmusic.core.model.PlayingMedia
import com.example.tcmusic.core.ui.PlayingMediaFloatingBar
import com.example.tcmusic.core.ui.util.PlayingMediaCompactTitleImageContentDescription
import com.example.tcmusic.core.ui.util.PlayingMediaImageContentDescription
import com.example.tcmusic.core.ui.util.PlayingMediaPauseContentDescription
import com.example.tcmusic.core.ui.util.PlayingMediaPlayContentDescription
import org.junit.Rule
import org.junit.Test

/**
 * Created by TC on 28/02/2023.
 */

class PlayingMediaFloatingBarTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testPlayingMediaImage_withImageNotNull() {
        val playingMedia = PlayingMedia(
            id = null,
            artist = null,
            image = "https://upload.wikimedia.org/wikipedia/en/7/7d/One_Direction_-_Story_of_My_Life_%28Official_Single_Cover%29.png",
            title = null,
            version = null
        )

        composeTestRule.setContent {
            Box {
                PlayingMediaFloatingBar(
                    playingMedia = playingMedia,
                    isPlaying = false,
                    onPlayingMediaClick = { },
                    onPlayClick = { },
                    onPauseClick = {  },
                    onSkipBackwardsClick = { },
                    onSkipForwardClick = { }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaImageContentDescription)
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaCompactTitleImageContentDescription)
            .assertDoesNotExist()
    }

    @Test
    fun testPlayingMediaImage_withImageNull() {
        val playingMedia = PlayingMedia(
            id = null,
            artist = null,
            image = null,
            title = null,
            version = null
        )

        composeTestRule.setContent {
            Box {
                PlayingMediaFloatingBar(
                    playingMedia = playingMedia,
                    isPlaying = false,
                    onPlayingMediaClick = { },
                    onPlayClick = { },
                    onPauseClick = {  },
                    onSkipBackwardsClick = { },
                    onSkipForwardClick = { }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaImageContentDescription)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaCompactTitleImageContentDescription)
            .assertExists()
    }

    @Test
    fun testPlayingMediaPlayPause_withIsPlayingTrue() {
        val playingMedia = PlayingMedia(
            id = null,
            artist = null,
            image = "https://upload.wikimedia.org/wikipedia/en/7/7d/One_Direction_-_Story_of_My_Life_%28Official_Single_Cover%29.png",
            title = null,
            version = null
        )

        composeTestRule.setContent {
            Box {
                PlayingMediaFloatingBar(
                    playingMedia = playingMedia,
                    isPlaying = true,
                    onPlayingMediaClick = { },
                    onPlayClick = { },
                    onPauseClick = {  },
                    onSkipBackwardsClick = { },
                    onSkipForwardClick = { }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaPlayContentDescription)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaPauseContentDescription)
            .assertExists()
    }

    @Test
    fun testPlayingMediaPlayPause_withIsPlayingFalse() {
        val playingMedia = PlayingMedia(
            id = null,
            artist = null,
            image = "https://upload.wikimedia.org/wikipedia/en/7/7d/One_Direction_-_Story_of_My_Life_%28Official_Single_Cover%29.png",
            title = null,
            version = null
        )

        composeTestRule.setContent {
            Box {
                PlayingMediaFloatingBar(
                    playingMedia = playingMedia,
                    isPlaying = false,
                    onPlayingMediaClick = { },
                    onPlayClick = { },
                    onPauseClick = {  },
                    onSkipBackwardsClick = { },
                    onSkipForwardClick = { }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaPlayContentDescription)
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaPauseContentDescription)
            .assertDoesNotExist()
    }
}