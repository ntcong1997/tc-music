package com.example.tcmusic.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by TC on 26/01/2023.
 */

@Serializable
data class Genres(
    @SerialName("primary")
    val primary: String?
)
