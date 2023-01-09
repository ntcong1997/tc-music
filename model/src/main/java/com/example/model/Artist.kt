package com.example.model
import com.google.gson.annotations.SerializedName

/**
 * Created by TC on 06/01/2023.
 */

data class Artist(
    @SerializedName("adamid")
    val adamid: String?,
    @SerializedName("alias")
    val alias: String?,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("data")
    val data: List<Data>?,
    @SerializedName("name")
    val name: String?,
) {
    // Api get artist detail not return name and avatar in this layer
    val artistName: String?
        get() = if (name.isNullOrBlank()) data?.firstOrNull()?.attributes?.name else name

    val artistAvatar: String?
        get() = if (avatar.isNullOrBlank()) data?.firstOrNull()?.attributes?.artwork?.url else avatar

    val shortArtistName: String
        get() {
            val artistNameDivided = artistName?.split(" ") ?: listOf()
            val firstLetter = artistNameDivided.firstOrNull()?.firstOrNull()?.uppercase() ?: ""
            val lastLetter = if (artistNameDivided.size < 2) "" else artistNameDivided.lastOrNull()?.firstOrNull()?.uppercase() ?: ""
            return "$firstLetter$lastLetter"
        }
}

data class Data(
    @SerializedName("attributes")
    val attributes: Attributes?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("relationships")
    val relationships: Relationships?,
    @SerializedName("type")
    val type: String?
)

data class Attributes(
    @SerializedName("artistBio")
    val artistBio: String?,
    @SerializedName("artwork")
    val artwork: Artwork?,
    @SerializedName("genreNames")
    val genreNames: List<String?>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin")
    val origin: String?,
    @SerializedName("url")
    val url: String?
)

data class Relationships(
    @SerializedName("albums")
    val albums: Albums?
)

data class Artwork(
    @SerializedName("url")
    val url: String?,
)

data class Albums(
    @SerializedName("data")
    val data: List<AlbumsData>?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("next")
    val next: String?
)

data class AlbumsData(
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)
