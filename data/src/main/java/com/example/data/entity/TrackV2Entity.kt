package com.example.data.entity
import com.google.gson.annotations.SerializedName

/**
 * Created by TC on 26/01/2023.
 */

data class TrackV2Entity(
    @SerializedName("resources")
    val resources: Resources?
)

data class Resources(
    @SerializedName("albums")
    val albums: Map<String, Any?>?,
    @SerializedName("artists")
    val artists: Map<String, Any?>?,
    @SerializedName("lyrics")
    val lyrics: Map<String, Any?>?,
    @SerializedName("related-tracks")
    val relatedTracks: Map<String, Any?>?,
    @SerializedName("shazam-songs")
    val shazamSongs: Map<String, Any?>?,
)

data class Album(
    @SerializedName("attributes")
    val attributes: AlbumAttributes?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class AlbumAttributes(
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("releaseDate")
    val releaseDate: String?
)

data class Lyric(
    @SerializedName("attributes")
    val attributes: LyricAttributes?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class LyricAttributes(
    @SerializedName("footer")
    val footer: String?,
    @SerializedName("musixmatchLyricsId")
    val musixmatchLyricsId: String?,
    @SerializedName("providerName")
    val providerName: String?,
    @SerializedName("syncAvailable")
    val syncAvailable: Boolean?,
    @SerializedName("text")
    val text: List<String?>?
)

data class ShazamSong(
    @SerializedName("attributes")
    val attributes: ShazamSongAttributes?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class ShazamSongAttributes(
    @SerializedName("artist")
    val artist: String?,
    @SerializedName("explicit")
    val explicit: Boolean?,
    @SerializedName("genres")
    val genres: Genres?,
    @SerializedName("images")
    val images: ShazamSongAttributesImages?,
    @SerializedName("isrc")
    val isrc: String?,
    @SerializedName("label")
    val label: String?,
    @SerializedName("primaryArtist")
    val primaryArtist: String?,
    @SerializedName("share")
    val share: ShazamSongAttributesShare?,
    @SerializedName("streaming")
    val streaming: Streaming?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("webUrl")
    val webUrl: String?
)

data class ShazamSongAttributesImages(
    @SerializedName("artistAvatar")
    val artistAvatar: String?,
    @SerializedName("coverArt")
    val coverArt: String?,
    @SerializedName("coverArtHq")
    val coverArtHq: String?
)

data class ShazamSongAttributesShare(
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

data class Streaming(
    @SerializedName("deeplink")
    val deeplink: String?,
    @SerializedName("preview")
    val preview: String?,
    @SerializedName("store")
    val store: String?
)
