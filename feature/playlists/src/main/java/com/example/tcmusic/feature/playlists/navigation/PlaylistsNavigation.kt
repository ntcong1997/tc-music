package com.example.tcmusic.feature.playlists.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tcmusic.feature.playlists.PlaylistsRoute

/**
 * Created by TC on 21/02/2023.
 */

const val playlistsRoute = "playlists_route"

fun NavController.navigateToPlaylists() {
    this.navigate(playlistsRoute)
}

fun NavGraphBuilder.playlistsScreen(

) {
    composable(route = playlistsRoute) {
        PlaylistsRoute()
    }
}