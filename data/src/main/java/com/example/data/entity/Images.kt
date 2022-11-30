package com.example.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by TC on 30/11/2022.
 */

data class Images(
    @SerializedName("artistAvatar")
    val artistAvatar: String?,
    @SerializedName("coverArt")
    val coverArt: String?,
    @SerializedName("coverArtHq")
    val coverArtHq: String?
)
