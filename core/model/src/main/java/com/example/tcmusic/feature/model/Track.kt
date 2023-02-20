package com.example.tcmusic.feature.model

/**
 * Created by TC on 14/10/2022.
 */

data class Track(
    val id: String?,
    val image: String?,
    val genre: String?,
    val lyrics: List<String>,
    val title: String?,
    val subTitle: String?,
    val uri: String?,
    // Because get track detail sometimes v1 sometimes v2 so we need this variable to know exactly which api to call to get track detail
    val version: String?
)
