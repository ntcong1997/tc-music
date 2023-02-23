package com.example.tcmusic.core.network.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by TC on 26/01/2023.
 */

@Serializable
data class NetworkTrackV2(
    @SerialName("resources")
    val resources: Resources?
)

@Serializable
data class Resources(
    @SerialName("albums")
    val albums: Map<String, Album?>?,
    @SerialName("artists")
    val artists: Map<String, NetworkArtist?>?,
    @SerialName("lyrics")
    val lyrics: Map<String, Lyric?>?,
    @SerialName("related-tracks")
    val relatedTracks: Map<String, RelatedTrack?>?,
    @SerialName("shazam-songs")
    val shazamSongs: Map<String, ShazamSong?>?,
)

@Serializable
data class Album(
    @SerialName("attributes")
    val attributes: AlbumAttributes?,
    @SerialName("id")
    val id: String?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class AlbumAttributes(
    @SerialName("artistName")
    val artistName: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("releaseDate")
    val releaseDate: String?
)

@Serializable
data class Lyric(
    @SerialName("attributes")
    val attributes: LyricAttributes?,
    @SerialName("href")
    val href: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class LyricAttributes(
    @SerialName("footer")
    val footer: String?,
    @SerialName("musixmatchLyricsId")
    val musixmatchLyricsId: String?,
    @SerialName("providerName")
    val providerName: String?,
    @SerialName("syncAvailable")
    val syncAvailable: Boolean?,
    @SerialName("text")
    val text: List<String?>?
)

@Serializable
data class RelatedTrack(
    @SerialName("id")
    val id: String?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class ShazamSong(
    @SerialName("attributes")
    val attributes: ShazamSongAttributes?,
    @SerialName("id")
    val id: String?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class ShazamSongAttributes(
    @SerialName("artist")
    val artist: String?,
    @SerialName("explicit")
    val explicit: Boolean?,
    @SerialName("genres")
    val genres: Genres?,
    @SerialName("images")
    val images: ShazamSongAttributesImages?,
    @SerialName("isrc")
    val isrc: String?,
    @SerialName("label")
    val label: String?,
    @SerialName("primaryArtist")
    val primaryArtist: String?,
    @SerialName("share")
    val share: ShazamSongAttributesShare?,
    @SerialName("streaming")
    val streaming: Streaming?,
    @SerialName("title")
    val title: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("webUrl")
    val webUrl: String?
)

@Serializable
data class ShazamSongAttributesImages(
    @SerialName("artistAvatar")
    val artistAvatar: String?,
    @SerialName("coverArt")
    val coverArt: String?,
    @SerialName("coverArtHq")
    val coverArtHq: String?
)

@Serializable
data class ShazamSongAttributesShare(
    @SerialName("href")
    val href: String?,
    @SerialName("html")
    val html: String?,
    @SerialName("image")
    val image: String?,
    @SerialName("snapchat")
    val snapchat: String?,
    @SerialName("subject")
    val subject: String?,
    @SerialName("text")
    val text: String?,
    @SerialName("twitter")
    val twitter: String?
)

@Serializable
data class Streaming(
    @SerialName("deeplink")
    val deeplink: String?,
    @SerialName("preview")
    val preview: String?,
    @SerialName("store")
    val store: String?
)
