package com.example.tcmusic.ui.main.main.home.electronicchart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.model.Track
import com.example.tcmusic.ui.main.main.components.TrackItem
import java.util.*

/**
 * Created by TC on 25/11/2022.
 */

@ExperimentalMaterialApi
@Composable
fun ElectronicChartScreen(
    viewModel: ElectronicChartViewModel = hiltViewModel()
) {
    val tracks = viewModel.tracks.collectAsLazyPagingItems()
    val isRefreshing = viewModel.isRefreshing.collectAsState()

    ElectronicChartScreen(
        tracks = tracks,
        isRefreshing = isRefreshing.value,
        onRefreshTracks = viewModel::refresh,
        onClickTrack = viewModel::clickTrack
    )
}

@ExperimentalMaterialApi
@Composable
fun ElectronicChartScreen(
    tracks: LazyPagingItems<Track>,
    isRefreshing: Boolean,
    onRefreshTracks: () -> Unit,
    onClickTrack: (Track) -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            onRefreshTracks()
        }
    )

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
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
                            onClickTrack(it)
                        }
                    )
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(
                Alignment.TopCenter
            )
        )
    }
}
