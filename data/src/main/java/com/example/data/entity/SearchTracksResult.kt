package com.example.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by TC on 06/01/2023.
 */

data class SearchTracksResult(
    @SerializedName("tracks")
    val tracks: Tracks?
)

data class Tracks(
    @SerializedName("hits")
    val hits: List<TracksHit>?
)

data class TracksHit(
    @SerializedName("track")
    val track: TrackEntity?
)
