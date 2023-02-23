package com.example.tcmusic.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by TC on 06/01/2023.
 */

@Serializable
data class NetworkArtist(
    @SerialName("adamid")
    val adamid: String?,
    @SerialName("alias")
    val alias: String?,
    @SerialName("avatar")
    val avatar: String?,
    @SerialName("data")
    val data: List<Data>?,
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?
) {
    val artistId: String?
        get() = adamid ?: data?.firstOrNull()?.id

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

@Serializable
data class Data(
    @SerialName("attributes")
    val attributes: Attributes?,
    @SerialName("href")
    val href: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("relationships")
    val relationships: Relationships?,
    @SerialName("type")
    val type: String?,
    @SerialName("views")
    val views: Views?
)

@Serializable
data class Attributes(
    @SerialName("artistBio")
    val artistBio: String?,
    @SerialName("artwork")
    val artwork: Artwork?,
    @SerialName("genreNames")
    val genreNames: List<String?>?,
    @SerialName("name")
    val name: String?,
    @SerialName("origin")
    val origin: String?,
    @SerialName("url")
    val url: String?
)

@Serializable
data class Relationships(
    @SerialName("albums")
    val albums: Albums?
)

@Serializable
data class Artwork(
    @SerialName("url")
    val url: String?,
)

@Serializable
data class Albums(
    @SerialName("data")
    val data: List<AlbumsData>?,
    @SerialName("href")
    val href: String?,
    @SerialName("next")
    val next: String?
)

@Serializable
data class AlbumsData(
    @SerialName("href")
    val href: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class Views(
    @SerialName("top-songs")
    val topSongs: TopSongs?
)

@Serializable
data class TopSongs(
    @SerialName("attributes")
    val attributes: TopSongsAttributes?,
    @SerialName("data")
    val data: List<TopSongsData>?,
    @SerialName("href")
    val href: String?,
    @SerialName("next")
    val next: String?
)

@Serializable
data class TopSongsAttributes(
    @SerialName("title")
    val title: String?
)

@Serializable
data class TopSongsData(
    @SerialName("attributes")
    val attributes: TopSongsDataAttributes?,
    @SerialName("href")
    val href: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class TopSongsDataAttributes(
    @SerialName("albumName")
    val albumName: String?,
    @SerialName("artistName")
    val artistName: String?,
    @SerialName("artwork")
    val artwork: Artwork?,
    @SerialName("audioLocale")
    val audioLocale: String?,
    @SerialName("audioTraits")
    val audioTraits: List<String>?,
    @SerialName("composerName")
    val composerName: String?,
    @SerialName("discNumber")
    val discNumber: Int?,
    @SerialName("durationInMillis")
    val durationInMillis: Int?,
    @SerialName("genreNames")
    val genreNames: List<String>?,
    @SerialName("hasLyrics")
    val hasLyrics: Boolean?,
    @SerialName("hasTimeSyncedLyrics")
    val hasTimeSyncedLyrics: Boolean?,
    @SerialName("isAppleDigitalMaster")
    val isAppleDigitalMaster: Boolean?,
    @SerialName("isMasteredForItunes")
    val isMasteredForItunes: Boolean?,
    @SerialName("isVocalAttenuationAllowed")
    val isVocalAttenuationAllowed: Boolean?,
    @SerialName("isrc")
    val isrc: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("previews")
    val previews: List<Preview>?,
    @SerialName("releaseDate")
    val releaseDate: String?,
    @SerialName("trackNumber")
    val trackNumber: Int?,
    @SerialName("url")
    val url: String?
)

@Serializable
data class Preview(
    @SerialName("url")
    val url: String?
)
