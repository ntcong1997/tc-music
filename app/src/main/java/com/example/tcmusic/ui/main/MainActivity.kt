package com.example.tcmusic.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.player.service.MediaNotificationManager
import com.example.tcmusic.ui.main.main.MainScreen
import com.example.tcmusic.ui.main.trackdetail.TrackDetailScreen
import com.example.tcmusic.ui.theme.TCMusicTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @FlowPreview
    @ExperimentalCoroutinesApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
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

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(route = Screen.MainScreen.route) {
                            MainScreen(navController)
                        }
                        composable(route = Screen.TrackDetailScreen.route) {
                            TrackDetailScreen(
                                navController = navController
                            )
                        }
                    }
                }

                LaunchedEffect(key1 = Unit) {
                    viewModel.navigateToTrackDetail.collect {
                        navController.navigate(Screen.TrackDetailScreen.route)
                    }
                }
            }
        }

        intent?.extras?.let {
            it.getString(MediaNotificationManager.EXTRA_DATA_TRACK_ID)?.let {
                viewModel.openTrackDetail()
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.extras?.let {
            it.getString(MediaNotificationManager.EXTRA_DATA_TRACK_ID)?.let {
                viewModel.openTrackDetail()
            }
        }
    }
}
