package com.example.tcmusic.core.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by TC on 26/01/2023.
 */

data class Genres(
    @SerializedName("primary")
    val primary: String?
)
