package com.example.tcmusic.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by TC on 06/01/2023.
 */

@Serializable
data class NetworkSearchArtists(
    @SerialName("artists")
    val artists: Artists?
)

@Serializable
data class Artists(
    @SerialName("hits")
    val hits: List<ArtistsHit>?
)

@Serializable
data class ArtistsHit(
    @SerialName("artist")
    val artist: NetworkArtist?
)
