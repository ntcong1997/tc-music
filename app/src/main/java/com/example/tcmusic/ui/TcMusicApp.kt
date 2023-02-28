package com.example.tcmusic.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.example.tcmusic.core.designsystem.icon.Icon
import com.example.tcmusic.core.designsystem.theme.BlueRibbon
import com.example.tcmusic.core.designsystem.theme.GraySilverChalice
import com.example.tcmusic.core.designsystem.theme.White
import com.example.tcmusic.core.model.PlayingMedia
import com.example.tcmusic.core.ui.PlayingMediaFloatingBar
import com.example.tcmusic.navigation.TcMusicNavHost
import com.example.tcmusic.navigation.TopLevelDestination

/**
 * Created by TC on 21/02/2023.
 */

@Composable
fun TcMusicApp(
    navController: NavHostController,
    playingMedia: PlayingMedia?,
    isPlaying: Boolean,
    onPlayingMediaClick: (String?, String?) -> Unit,
    onPlayingMediaPlayClick: () -> Unit,
    onPlayingMediaPauseClick: () -> Unit,
    onPlayingMediaSkipBackwardsClick: () -> Unit,
    onPlayingMediaSkipForwardClick: () -> Unit,
    appState: TcMusicAppState = rememberTcMusicAppState(navController)
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    TcMusicBottomNavigation(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination
                    )
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        PaddingValues(
                            0.dp,
                            0.dp,
                            0.dp,
                            it.calculateBottomPadding()
                        )
                    )
            ) {
                TcMusicNavHost(navController = appState.navController)

                if (appState.currentTopLevelDestination != TopLevelDestination.Track && playingMedia != null) {
                    PlayingMediaFloatingBar(
                        playingMedia = playingMedia,
                        isPlaying = isPlaying,
                        onClick = {
                            onPlayingMediaClick(it.id, it.version)
                        },
                        onPlayClick = onPlayingMediaPlayClick,
                        onPauseClick = onPlayingMediaPauseClick,
                        onSkipBackwardsClick = onPlayingMediaSkipBackwardsClick,
                        onSkipForwardClick = onPlayingMediaSkipForwardClick
                    )
                }
            }
        }
    }
}

@Composable
private fun TcMusicBottomNavigation(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    BottomNavigation(
        backgroundColor = White,
        elevation = 8.dp
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            when (destination) {
                is TopLevelDestination.Home -> {
                    TcMusicBottomNavigationItem(
                        icon = destination.icon,
                        iconTextId = destination.iconTextId,
                        selected = selected,
                        onClick = {
                            onNavigateToDestination(destination)
                        }
                    )
                }
                is TopLevelDestination.Search -> {
                    TcMusicBottomNavigationItem(
                        icon = destination.icon,
                        iconTextId = destination.iconTextId,
                        selected = selected,
                        onClick = {
                            onNavigateToDestination(destination)
                        }
                    )
                }
                is TopLevelDestination.Playlists -> {
                    TcMusicBottomNavigationItem(
                        icon = destination.icon,
                        iconTextId = destination.iconTextId,
                        selected = selected,
                        onClick = {
                            onNavigateToDestination(destination)
                        }
                    )
                }
                is TopLevelDestination.Settings -> {
                    TcMusicBottomNavigationItem(
                        icon = destination.icon,
                        iconTextId = destination.iconTextId,
                        selected = selected,
                        onClick = {
                            onNavigateToDestination(destination)
                        }
                    )
                }
                else -> Unit
            }
        }
    }
}

@Composable
fun RowScope.TcMusicBottomNavigationItem(
    icon: Icon,
    iconTextId: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    BottomNavigationItem(
        icon = {
            when (icon) {
                is Icon.ImageVectorIcon -> Icon(
                    imageVector = icon.imageVector,
                    contentDescription = null,
                )

                is Icon.DrawableResourceIcon -> Icon(
                    painter = painterResource(id = icon.id),
                    contentDescription = null,
                )
            }
        },
        label = {
            Text(
                text = stringResource(id = iconTextId),
                fontSize = 10.sp,
                maxLines = 1
            )
        },
        selectedContentColor = BlueRibbon,
        unselectedContentColor = GraySilverChalice,
        selected = selected,
        onClick = onClick
    )
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
