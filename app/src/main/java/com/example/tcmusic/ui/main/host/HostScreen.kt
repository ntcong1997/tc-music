package com.example.tcmusic.ui.main.host

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.model.PlayingMediaInfo
import com.example.tcmusic.R
import com.example.tcmusic.core.designsystem.theme.BlueRibbon
import com.example.tcmusic.core.designsystem.theme.GraySilverChalice
import com.example.tcmusic.ui.main.main.home.HomeScreen
import com.example.tcmusic.ui.main.main.playlists.PlaylistsScreen
import com.example.tcmusic.ui.main.main.search.SearchScreen
import com.example.tcmusic.ui.main.main.settings.SettingsScreen
import com.example.tcmusic.ui.theme.BlueRibbon
import com.example.tcmusic.ui.theme.GraySilverChalice
import com.example.tcmusic.ui.theme.White
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * Created by TC on 02/12/2022.
 */

@Composable
fun HostRoute(
    viewModel: HostViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val playingMediaInfo = viewModel.playingMediaInfo.collectAsState()
    val isPlaying = viewModel.isPlaying.collectAsState()

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
                    Home()
                }
                composable(route = Screen.Search.route) {
                    Search(
                        mainNavController = mainNavController
                    )
                }
                composable(route = Screen.Playlists.route) {
                    Playlists()
                }
                composable(route = Screen.Settings.route) {
                    Settings()
                }
            }

            if (playingMediaInfo.value != null) {
                PlayingMediaInfoBar(
                    playingMediaInfo = playingMediaInfo.value!!,
                    isPlaying = isPlaying.value,
                    onClickPlayingMediaInfo = {
                        mainNavController.navigate(com.example.tcmusic.ui.main.Screen.TrackDetailScreen.route + "?trackId=${it.id}&trackVersion=${it.version}")
                    },
                    onClickPlay = viewModel::clickPlay,
                    onClickPause = viewModel::clickPause,
                    onClickSkipBackwards = viewModel::clickSkipBackwards,
                    onClickSkipForward = viewModel::clickSkipForward
                )
            }
        }
    }
}

@Composable
fun HostBottomBar(
    navController: NavController
) {
    val items = listOf(
        Screen.Home,
        Screen.Search,
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

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
