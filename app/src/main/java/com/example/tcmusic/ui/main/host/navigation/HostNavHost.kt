package com.example.tcmusic.ui.main.host.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.tcmusic.feature.artist.navigation.navigateToArtist
import com.example.tcmusic.feature.home.navigation.homeRoute
import com.example.tcmusic.feature.home.navigation.homeScreen
import com.example.tcmusic.feature.playlists.navigation.playlistsScreen
import com.example.tcmusic.feature.search.navigation.searchScreen
import com.example.tcmusic.feature.settings.navigation.settingsScreen

/**
 * Created by TC on 21/02/2023.
 */

@Composable
fun HostNavHost(
    navController: NavHostController,
    startDestination: String = homeRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen()
        searchScreen(
            onArtistClick = {
                navController.navigateToArtist(it)
            }
        )
        playlistsScreen()
        settingsScreen()
    }
}