package com.example.tcmusic.feature.home.worldchart

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.tcmusic.feature.home.Chart

/**
 * Created by TC on 13/10/2022.
 */

@ExperimentalMaterialApi
@Composable
fun WorldChartScreen(
    viewModel: WorldChartViewModel = hiltViewModel()
) {
    val tracks = viewModel.tracks.collectAsLazyPagingItems()
    val isRefreshing = viewModel.isRefreshing.collectAsState()

    Chart(
        tracks = tracks,
        isRefreshing = isRefreshing.value,
        onTracksRefresh = viewModel::refresh,
        onTrackClick = viewModel::clickTrack
    )
}
