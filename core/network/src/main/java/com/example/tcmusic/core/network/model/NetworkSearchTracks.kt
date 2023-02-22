package com.example.tcmusic.core.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by TC on 06/01/2023.
 */

data class NetworkSearchTracks(
    @SerializedName("tracks")
    val tracks: Tracks?
)

data class Tracks(
    @SerializedName("hits")
    val hits: List<TracksHit>?
)

data class TracksHit(
    @SerializedName("track")
    val track: NetworkTrackV1?
)
