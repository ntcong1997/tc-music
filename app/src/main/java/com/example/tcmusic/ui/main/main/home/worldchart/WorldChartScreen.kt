package com.example.tcmusic.ui.main.main.home.worldchart

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.model.Track
import com.example.tcmusic.ui.main.Screen
import com.example.tcmusic.ui.main.main.home.TrackItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.UUID

/**
 * Created by TC on 13/10/2022.
 */

@Composable
fun WorldChartScreen(
    mainNavController: NavController,
    viewModel: WorldChartViewModel = hiltViewModel()
) {
    val tracks = viewModel.tracks.collectAsLazyPagingItems()

    WorldChartScreen(
        tracks = tracks,
        onClickTrack = {
            mainNavController.navigate(Screen.TrackDetailScreen.route + "?trackId=$it")
        }
    )
}

@Composable
fun WorldChartScreen(
    tracks: LazyPagingItems<Track>,
    onClickTrack: (String?) -> Unit
) {
    var isRefreshing by remember { mutableStateOf(false) }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            tracks.refresh()
            isRefreshing = false
        }
    ) {
        LazyColumn {
            items(
                items = tracks,
                key = {
                    it.key ?: UUID.randomUUID().toString()
                }
            ) { itemTrack ->
                itemTrack?.let {
                    TrackItem(
                        track = it,
                        onClickTrack = {
                            onClickTrack(it.key)
                        }
                    )
                }
            }
        }
    }
}