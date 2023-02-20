package com.example.tcmusic.feature.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by TC on 06/01/2023.
 */

data class NetworkSearchArtists(
    @SerializedName("artists")
    val artists: Artists?
)

data class Artists(
    @SerializedName("hits")
    val hits: List<ArtistsHit>?
)

data class ArtistsHit(
    @SerializedName("artist")
    val artist: NetworkArtist?
)
