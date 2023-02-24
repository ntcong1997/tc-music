package com.example.tcmusic.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.tcmusic.core.designsystem.theme.TCMusicTheme
import com.example.tcmusic.core.player.service.MediaNotificationManager
import com.example.tcmusic.feature.track.navigation.navigateToTrack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }

        setContent {
            TCMusicTheme {
                val navController = rememberNavController()

                val playingMedia = viewModel.playingMediaInfo.collectAsState()
                val isPlaying = viewModel.isPlaying.collectAsState()

                TcMusicApp(
                    navController = navController,
                    playingMedia = playingMedia.value,
                    isPlaying = isPlaying.value,
                    onPlayingMediaClick = { trackId, trackVersion ->
                        navController.navigateToTrack(trackId, trackVersion)
                    },
                    onPlayingMediaPlayClick = viewModel::clickPlay,
                    onPlayingMediaPauseClick = viewModel::clickPause,
                    onPlayingMediaSkipBackwardsClick = viewModel::clickSkipBackwards,
                    onPlayingMediaSkipForwardClick = viewModel::clickSkipForward
                )

                LaunchedEffect(key1 = Unit) {
                    viewModel.navigateToTrackDetail.collect {
                        navController.navigateToTrack(it.trackId, it.trackVersion)
                    }
                }
            }
        }

        intent?.extras?.let {
            openTrackDetail(
                it.getString(MediaNotificationManager.EXTRA_DATA_TRACK_ID),
                it.getString(MediaNotificationManager.EXTRA_DATA_TRACK_VERSION)
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.extras?.let {
            openTrackDetail(
                it.getString(MediaNotificationManager.EXTRA_DATA_TRACK_ID),
                it.getString(MediaNotificationManager.EXTRA_DATA_TRACK_VERSION)
            )
        }
    }

    private fun openTrackDetail(trackId: String?, trackVersion: String?) {
        viewModel.openTrackDetail(trackId, trackVersion)
    }
}
