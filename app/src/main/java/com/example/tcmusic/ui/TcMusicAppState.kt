package com.example.tcmusic.ui

import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.example.tcmusic.feature.artist.navigation.artistRoute
import com.example.tcmusic.feature.home.navigation.homeRoute
import com.example.tcmusic.feature.home.navigation.navigateToHome
import com.example.tcmusic.feature.playlists.navigation.navigateToPlaylists
import com.example.tcmusic.feature.playlists.navigation.playlistsRoute
import com.example.tcmusic.feature.search.navigation.navigateToSearch
import com.example.tcmusic.feature.search.navigation.searchRoute
import com.example.tcmusic.feature.settings.navigation.navigateToSettings
import com.example.tcmusic.feature.settings.navigation.settingsRoute
import com.example.tcmusic.feature.track.navigation.trackRoute
import com.example.tcmusic.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

/**
 * Created by TC on 21/02/2023.
 */

@Composable
fun rememberTcMusicAppState(
    navController: NavHostController,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): TcMusicAppState {
    return remember(navController, coroutineScope) {
        TcMusicAppState(navController, coroutineScope)
    }
}

@Stable
class TcMusicAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    // Determine current top level destination
    // Route must be split / because artistRoute and trackRoute
    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route?.split("/")?.firstOrNull()) {
            homeRoute -> TopLevelDestination.Home()
            searchRoute -> TopLevelDestination.Search()
            playlistsRoute -> TopLevelDestination.Playlists()
            settingsRoute -> TopLevelDestination.Settings()
            artistRoute -> TopLevelDestination.Artist
            trackRoute -> TopLevelDestination.Track
            else -> null
        }

    val shouldShowBottomBar: Boolean
        @Composable get() = currentTopLevelDestination != TopLevelDestination.Artist &&
            currentTopLevelDestination != TopLevelDestination.Track

    /**
     * Map of top level destinations to be used in the BottomBar. The key is the route
     */
    val topLevelDestinations: List<TopLevelDestination> = listOf(
        TopLevelDestination.Home(),
        TopLevelDestination.Search(),
        TopLevelDestination.Playlists(),
        TopLevelDestination.Settings()
    )

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                is TopLevelDestination.Home -> navController.navigateToHome(topLevelNavOptions)
                is TopLevelDestination.Search -> navController.navigateToSearch(topLevelNavOptions)
                is TopLevelDestination.Playlists -> navController.navigateToPlaylists(topLevelNavOptions)
                is TopLevelDestination.Settings -> navController.navigateToSettings(topLevelNavOptions)
                else -> Unit
            }
        }
    }
}
