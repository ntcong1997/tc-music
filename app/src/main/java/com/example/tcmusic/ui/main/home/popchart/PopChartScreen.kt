package com.example.tcmusic.ui.main.home.popchart

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
 * Created by TC on 22/11/2022.
 */

@Composable
fun PopChartScreen(
    viewModel: PopChartViewModel = hiltViewModel()
) {
    val tracks = viewModel.tracks.collectAsLazyPagingItems()

    PopChartScreen(tracks = tracks)
}

@Composable
fun PopChartScreen(
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