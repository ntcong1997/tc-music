package com.example.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by TC on 30/11/2022.
 */

data class Share(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("html")
    val html: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("snapchat")
    val snapchat: String?,
    @SerializedName("subject")
    val subject: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("twitter")
    val twitter: String?
)
