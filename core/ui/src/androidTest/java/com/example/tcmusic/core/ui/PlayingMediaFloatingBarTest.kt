package com.example.tcmusic.core.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.tcmusic.core.model.PlayingMedia
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

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
            title = "Story of my life",
            version = null
        )

        composeTestRule.setContent {
            Box {
                PlayingMediaFloatingBar(
                    playingMedia = playingMedia,
                    isPlaying = false,
                    onClick = { },
                    onPlayClick = { },
                    onPauseClick = { },
                    onSkipBackwardsClick = { },
                    onSkipForwardClick = { }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaImageContentDescription)
            .assertExists()

        composeTestRule
            .onNodeWithText("SL")
            .assertDoesNotExist()
    }

    @Test
    fun testPlayingMediaImage_withImageNull() {
        val playingMedia = PlayingMedia(
            id = null,
            artist = null,
            image = null,
            title = "Story of my life",
            version = null
        )

        composeTestRule.setContent {
            Box {
                PlayingMediaFloatingBar(
                    playingMedia = playingMedia,
                    isPlaying = false,
                    onClick = { },
                    onPlayClick = { },
                    onPauseClick = { },
                    onSkipBackwardsClick = { },
                    onSkipForwardClick = { }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaImageContentDescription)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText("SL")
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
                    onClick = { },
                    onPlayClick = { },
                    onPauseClick = { },
                    onSkipBackwardsClick = { },
                    onSkipForwardClick = { }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaPlayIconContentDescription)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaPauseIconContentDescription)
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
                    onClick = { },
                    onPlayClick = { },
                    onPauseClick = { },
                    onSkipBackwardsClick = { },
                    onSkipForwardClick = { }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaPlayIconContentDescription)
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaPauseIconContentDescription)
            .assertDoesNotExist()
    }
    
    @Test
    fun testPlayingMedia_performClick_withIsPlayingFalse() {
        var onClickCalled = false
        var onPlayClickCalled = false
        var onSkipBackwardsClickCalled = false
        var onSkipForwardClickCalled = false

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
                    onClick = {
                        assertEquals(playingMedia, it)
                        onClickCalled = true
                    },
                    onPlayClick = {
                        onPlayClickCalled = true
                    },
                    onPauseClick = {

                    },
                    onSkipBackwardsClick = {
                        onSkipBackwardsClickCalled = true
                    },
                    onSkipForwardClick = { 
                        onSkipForwardClickCalled = true
                    }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaBarContentDescription)
            .performClick()

        assertTrue(onClickCalled)

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaPlayIconContentDescription)
            .performClick()

        assertTrue(onPlayClickCalled)

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaSkipBackwardsIconContentDescription)
            .performClick()

        assertTrue(onSkipBackwardsClickCalled)

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaSkipForwardIconContentDescription)
            .performClick()

        assertTrue(onSkipForwardClickCalled)
    }

    @Test
    fun testPlayingMedia_performClick_withIsPlayingTrue() {
        var onClickCalled = false
        var onPauseClickCalled = false
        var onSkipBackwardsClickCalled = false
        var onSkipForwardClickCalled = false

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
                    onClick = {
                        assertEquals(playingMedia, it)
                        onClickCalled = true
                    },
                    onPlayClick = {

                    },
                    onPauseClick = {
                        onPauseClickCalled = true
                    },
                    onSkipBackwardsClick = {
                        onSkipBackwardsClickCalled = true
                    },
                    onSkipForwardClick = {
                        onSkipForwardClickCalled = true
                    }
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaBarContentDescription)
            .performClick()

        assertTrue(onClickCalled)

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaPauseIconContentDescription)
            .performClick()

        assertTrue(onPauseClickCalled)

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaSkipBackwardsIconContentDescription)
            .performClick()

        assertTrue(onSkipBackwardsClickCalled)

        composeTestRule
            .onNodeWithContentDescription(PlayingMediaSkipForwardIconContentDescription)
            .performClick()

        assertTrue(onSkipForwardClickCalled)
    }
}