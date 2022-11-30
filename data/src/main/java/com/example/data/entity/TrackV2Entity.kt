package com.example.data.entity
import com.google.gson.annotations.SerializedName


/**
 * Created by TC on 30/11/2022.
 */

data class TrackV2Entity(
    @SerializedName("data")
    val data: List<Data>?,
    @SerializedName("resources")
    val resources: Resources?
)

data class Data(
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class Resources(
    @SerializedName("albums")
    val albums: Map<String, Albums?>?,
    @SerializedName("artist-highlights")
    val artistHighlights: Map<String, ArtistHighlights?>?,
    @SerializedName("artists")
    val artists: Map<String, Artists?>?,
    @SerializedName("lyrics")
    val lyrics: Map<String, Lyrics?>?,
    @SerializedName("related-tracks")
    val relatedTracks: Map<String, RelatedTracks?>?,
    @SerializedName("songs")
    val songs: Map<String, Songs?>?
)

data class Albums(
    @SerializedName("attributes")
    val attributes: Attributes?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class ArtistHighlights(
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class Artists(
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class Lyrics(
    @SerializedName("attributes")
    val attributes: AttributesX?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class RelatedTracks(
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class Songs(
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?
)

data class Attributes(
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("releaseDate")
    val releaseDate: String?
)

data class AttributesX(
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

data class ShazamSongs(
    @SerializedName("attributes")
    val attributes: AttributesXX?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("relationships")
    val relationships: Relationships?,
    @SerializedName("type")
    val type: String?
)

data class AttributesXX(
    @SerializedName("artist")
    val artist: String?,
    @SerializedName("explicit")
    val explicit: Boolean?,
    @SerializedName("genres")
    val genres: Genres?,
    @SerializedName("images")
    val images: Images?,
    @SerializedName("isrc")
    val isrc: String?,
    @SerializedName("label")
    val label: String?,
    @SerializedName("primaryArtist")
    val primaryArtist: String?,
    @SerializedName("share")
    val share: Share?,
    @SerializedName("streaming")
    val streaming: Streaming?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("webUrl")
    val webUrl: String?
)

data class Relationships(
    @SerializedName("albums")
    val albums: AlbumsX?,
    @SerializedName("artist-highlights")
    val artistHighlights: ArtistHighlightsX?,
    @SerializedName("artists")
    val artists: ArtistsX?,
    @SerializedName("lyrics")
    val lyrics: LyricsX?,
    @SerializedName("related-tracks")
    val relatedTracks: RelatedTracksX?,
    @SerializedName("shazam-artists")
    val shazamArtists: ShazamArtists?,
    @SerializedName("songs")
    val songs: SongsX?
)

data class Streaming(
    @SerializedName("deeplink")
    val deeplink: String?,
    @SerializedName("preview")
    val preview: String?,
    @SerializedName("store")
    val store: String?
)

data class AlbumsX(
    @SerializedName("data")
    val data: List<Data?>?
)

data class ArtistHighlightsX(
    @SerializedName("data")
    val data: List<Data>?
)

data class ArtistsX(
    @SerializedName("data")
    val data: List<Data>?
)

data class LyricsX(
    @SerializedName("data")
    val data: List<Data>?
)

data class RelatedTracksX(
    @SerializedName("data")
    val data: List<Data>?
)

data class ShazamArtists(
    @SerializedName("data")
    val data: List<Data>?
)

data class SongsX(
    @SerializedName("data")
    val data: List<Data>?
)