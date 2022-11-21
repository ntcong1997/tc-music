package com.example.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by TC on 14/10/2022.
 */

data class Track(
    @SerializedName("alias")
    val alias: String?,
    @SerializedName("artists")
    val artists: List<Artist?>?,
    @SerializedName("genres")
    val genres: Genres?,
    @SerializedName("hub")
    val hub: Hub?,
    @SerializedName("images")
    val images: Images?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("releasedate")
    val releaseDate: String?,
    @SerializedName("sections")
    val sections: List<Section?>?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)

data class Artist(
    @SerializedName("alias")
    val alias: String?,
    @SerializedName("id")
    val id: String?
)

data class Genres(
    @SerializedName("primary")
    val primary: String?
)

data class Hub(
    @SerializedName("actions")
    val actions: List<Action?>?,
    @SerializedName("image")
    val image: String?
)

data class Images(
    @SerializedName("background")
    val background: String?,
    @SerializedName("coverart")
    val coverArt: String?,
    @SerializedName("coverarthq")
    val coverArtHq: String?
)

data class Section(
    @SerializedName("text")
    val text: List<String?>?,
    @SerializedName("type")
    val type: String?
)

data class Action(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)