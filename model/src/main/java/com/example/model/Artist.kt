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

    val topSongs: List<TopSongsData>
        get() {
            return data?.firstOrNull()?.views?.topSongs?.data ?: listOf()
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
    val type: String?,
    @SerializedName("views")
    val views: Views?
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

data class Views(
    @SerializedName("top-songs")
    val topSongs: TopSongs?
)

data class TopSongs(
    @SerializedName("attributes")
    val attributes: TopSongsAttributes?,
    @SerializedName("data")
    val data: List<TopSongsData>?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("next")
    val next: String?
)

data class TopSongsAttributes(
    @SerializedName("title")
    val title: String?
)

data class TopSongsData(
    @SerializedName("attributes")
    val attributes: TopSongsDataAttributes?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class TopSongsDataAttributes(
    @SerializedName("albumName")
    val albumName: String?,
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("artwork")
    val artwork: Artwork?,
    @SerializedName("audioLocale")
    val audioLocale: String?,
    @SerializedName("audioTraits")
    val audioTraits: List<String>?,
    @SerializedName("composerName")
    val composerName: String?,
    @SerializedName("discNumber")
    val discNumber: Int?,
    @SerializedName("durationInMillis")
    val durationInMillis: Int?,
    @SerializedName("genreNames")
    val genreNames: List<String>?,
    @SerializedName("hasLyrics")
    val hasLyrics: Boolean?,
    @SerializedName("hasTimeSyncedLyrics")
    val hasTimeSyncedLyrics: Boolean?,
    @SerializedName("isAppleDigitalMaster")
    val isAppleDigitalMaster: Boolean?,
    @SerializedName("isMasteredForItunes")
    val isMasteredForItunes: Boolean?,
    @SerializedName("isVocalAttenuationAllowed")
    val isVocalAttenuationAllowed: Boolean?,
    @SerializedName("isrc")
    val isrc: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("previews")
    val previews: List<Preview>?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("trackNumber")
    val trackNumber: Int?,
    @SerializedName("url")
    val url: String?
)

data class Preview(
    @SerializedName("url")
    val url: String?
)
