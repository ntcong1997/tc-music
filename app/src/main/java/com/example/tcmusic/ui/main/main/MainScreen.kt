package com.example.tcmusic.ui.main.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tcmusic.ui.main.main.favorites.FavoritesScreen
import com.example.tcmusic.ui.main.main.home.HomeScreen
import com.example.tcmusic.ui.main.main.playlists.PlaylistsScreen
import com.example.tcmusic.ui.main.main.search.SearchScreen
import com.example.tcmusic.ui.main.main.settings.SettingsScreen
import com.example.tcmusic.ui.theme.BlueRibbon
import com.example.tcmusic.ui.theme.GraySilverChalice
import com.example.tcmusic.ui.theme.White
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * Created by TC on 02/12/2022.
 */

@ExperimentalPagerApi
@Composable
fun MainScreen(
    mainNavController: NavController
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            MainBottomNavigation(navController = navController)
        }
    ) {
        Box(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    it.calculateBottomPadding()
                )
            )
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route
            ) {
                composable(route = Screen.Home.route) {
                    Home(mainNavController)
                }
                composable(route = Screen.Search.route) {
                    Search()
                }
                composable(route = Screen.Favorites.route) {
                    Favorites()
                }
                composable(route = Screen.Playlists.route) {
                    Playlists()
                }
                composable(route = Screen.Settings.route) {
                    Settings()
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Home(
    mainNavController: NavController
) {
    HomeScreen(mainNavController)
}

@Composable
fun Search() {
    SearchScreen()
}

@Composable
fun Favorites() {
    FavoritesScreen()
}

@Composable
fun Playlists() {
    PlaylistsScreen()
}

@Composable
fun Settings() {
    SettingsScreen()
}

@Composable
fun MainBottomNavigation(
    navController: NavController
) {
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Favorites,
        Screen.Playlists,
        Screen.Settings
    )

    BottomNavigation(
        backgroundColor = White,
        elevation = 8.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = screen.resourceId),
                        fontSize = 10.sp,
                        maxLines = 1
                    )
                },
                selectedContentColor = BlueRibbon,
                unselectedContentColor = GraySilverChalice,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}