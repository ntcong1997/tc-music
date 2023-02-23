package com.example.tcmusic.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by TC on 26/01/2023.
 */

@Serializable
data class NetworkTrackV1(
    @SerialName("alias")
    val alias: String?,
    @SerialName("artists")
    val artists: List<NetworkArtist>?,
    @SerialName("genres")
    val genres: Genres?,
    @SerialName("hub")
    val hub: Hub?,
    @SerialName("images")
    val images: NetworkTrackV1Images?,
    @SerialName("key")
    val key: String?,
    @SerialName("layout")
    val layout: String?,
    @SerialName("releasedate")
    val releasedate: String?,
    @SerialName("sections")
    val sections: List<Section>?,
    @SerialName("share")
    val share: NetworkTrackV1Share?,
    @SerialName("subtitle")
    val subtitle: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("urlparams")
    val urlparams: Urlparams?
) {
    val mediaUri: String?
        get() = if (hub?.actions?.find { it.type == "uri" } != null) {
            val mediaAction = hub.actions.find { it.type == "uri" }
            mediaAction?.uri
        } else null

    val lyrics: List<String>
        get() = if (sections?.find { it.type == "LYRICS" } != null) {
            val sectionLyrics = sections.find { it.type == "LYRICS" }
            val lyrics = sectionLyrics?.text
            if (lyrics.isNullOrEmpty()) listOf()
            else lyrics
        } else listOf()
}

@Serializable
data class Hub(
    @SerialName("actions")
    val actions: List<Action>?,
    @SerialName("displayname")
    val displayname: String?,
    @SerialName("explicit")
    val explicit: Boolean?,
    @SerialName("image")
    val image: String?,
    @SerialName("options")
    val options: List<Option>?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class NetworkTrackV1Images(
    @SerialName("background")
    val background: String?,
    @SerialName("coverart")
    val coverart: String?,
    @SerialName("coverarthq")
    val coverarthq: String?,
    @SerialName("joecolor")
    val joecolor: String?
)

@Serializable
data class Section(
    @SerialName("beacondata")
    val beacondata: BeacondataX?,
    @SerialName("footer")
    val footer: String?,
    @SerialName("metadata")
    val metadata: List<Metadata>?,
    @SerialName("metapages")
    val metapages: List<Metapage>?,
    @SerialName("tabname")
    val tabname: String?,
    @SerialName("text")
    val text: List<String>?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class NetworkTrackV1Share(
    @SerialName("avatar")
    val avatar: String?,
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
data class Urlparams(
    @SerialName("{trackartist}")
    val trackartist: String?,
    @SerialName("{tracktitle}")
    val tracktitle: String?
)

@Serializable
data class Action(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("uri")
    val uri: String?
)

@Serializable
data class Option(
    @SerialName("actions")
    val actions: List<Action>?,
    @SerialName("beacondata")
    val beacondata: Beacondata?,
    @SerialName("caption")
    val caption: String?,
    @SerialName("colouroverflowimage")
    val colouroverflowimage: Boolean?,
    @SerialName("image")
    val image: String?,
    @SerialName("listcaption")
    val listcaption: String?,
    @SerialName("overflowimage")
    val overflowimage: String?,
    @SerialName("providername")
    val providername: String?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class Beacondata(
    @SerialName("providername")
    val providername: String?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class BeacondataX(
    @SerialName("commontrackid")
    val commontrackid: String?,
    @SerialName("lyricsid")
    val lyricsid: String?,
    @SerialName("providername")
    val providername: String?
)

@Serializable
data class Metadata(
    @SerialName("text")
    val text: String?,
    @SerialName("title")
    val title: String?
)

@Serializable
data class Metapage(
    @SerialName("caption")
    val caption: String?,
    @SerialName("image")
    val image: String?
)
