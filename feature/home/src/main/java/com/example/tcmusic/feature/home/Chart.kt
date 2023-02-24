package com.example.tcmusic.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.ui.TrackCard
import java.util.*

/**
 * Created by TC on 20/02/2023.
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chart(
    tracks: LazyPagingItems<Track>,
    isRefreshing: Boolean,
    onTracksRefresh: () -> Unit,
    onTrackClick: (Track) -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = onTracksRefresh
    )

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn {
            items(
                items = tracks,
                key = {
                    it.id ?: UUID.randomUUID().toString()
                }
            ) { itemTrack ->
                itemTrack?.let {
                    TrackCard(
                        track = it,
                        onClick = {
                            onTrackClick(it)
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
