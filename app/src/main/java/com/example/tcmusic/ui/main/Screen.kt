package com.example.tcmusic.ui.main

/**
 * Created by TC on 02/12/2022.
 */

sealed class Screen(val route: String) {
    object MainScreen : Screen("main")
    object TrackDetailScreen : Screen("track_detail")
}
