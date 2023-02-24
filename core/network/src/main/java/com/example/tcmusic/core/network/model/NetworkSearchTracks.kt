package com.example.tcmusic.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by TC on 06/01/2023.
 */

@Serializable
data class NetworkSearchTracks(
    @SerialName("tracks")
    val tracks: Tracks?
)

@Serializable
data class Tracks(
    @SerialName("hits")
    val hits: List<TracksHit>?
)

@Serializable
data class TracksHit(
    @SerialName("track")
    val track: NetworkTrackV1?
)
