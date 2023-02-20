package com.example.tcmusic.feature.model

/**
 * Created by TC on 06/01/2023.
 */

data class Artist(
    val avatar: String?,
    val id: String?,
    val name: String?,
    val topSongs: List<Track>
)
