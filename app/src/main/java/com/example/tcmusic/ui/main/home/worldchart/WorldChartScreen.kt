package com.example.tcmusic.ui.main.home.worldchart

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.domain.model.Track
import com.example.tcmusic.ui.main.home.TrackItem
import java.util.UUID

/**
 * Created by TC on 13/10/2022.
 */

@Composable
fun WorldChartScreen(
    viewModel: WorldChartViewModel = hiltViewModel()
) {
    val tracks = viewModel.tracks.collectAsLazyPagingItems()

    WorldChartScreen(tracks = tracks)
}

@Composable
fun WorldChartScreen(
    tracks: LazyPagingItems<Track>
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