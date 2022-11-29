package com.example.tcmusic.ui.main.home.hiphoprapchart

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.domain.model.Track
import com.example.tcmusic.ui.main.home.TrackItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.*

/**
 * Created by TC on 25/11/2022.
 */

@Composable
fun HipHopRapChartScreen(
    viewModel: HipHopRapChartViewModel = hiltViewModel()
) {
    val tracks = viewModel.tracks.collectAsLazyPagingItems()

    HipHopRapChartScreen(tracks = tracks)
}

@Composable
fun HipHopRapChartScreen(
    tracks: LazyPagingItems<Track>
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
                    TrackItem(track = it)
                }
            }
        }
    }
}