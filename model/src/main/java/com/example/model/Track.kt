package com.example.model

import com.google.gson.annotations.SerializedName

/**
 * Created by TC on 14/10/2022.
 */

data class Track(
    @SerializedName("alias")
    val alias: String?,
    @SerializedName("artists")
    val artists: List<Artist>?,
    @SerializedName("genres")
    val genres: Genres?,
    @SerializedName("hub")
    val hub: Hub?,
    @SerializedName("images")
    val images: Images?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("layout")
    val layout: String?,
    @SerializedName("releasedate")
    val releasedate: String?,
    @SerializedName("sections")
    val sections: List<Section>?,
    @SerializedName("share")
    val share: Share?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlparams")
    val urlparams: Urlparams?
) {
    val mediaUri: String
        get() = if (hub?.actions?.find { it.type == "uri" } != null) {
            val mediaAction = hub.actions.find { it.type == "uri" }
            mediaAction?.uri ?: ""
        } else ""

    val lyrics: List<String>
        get() = if (sections?.find { it.type == "LYRICS" } != null) {
            val sectionLyrics = sections.find { it.type == "LYRICS" }
            val lyrics = sectionLyrics?.text
            if (lyrics.isNullOrEmpty()) listOf()
            else lyrics
        } else listOf()

    val shortTitle: String
        get() {
            val titleDivided = title?.split(" ") ?: listOf()
            val firstLetter = titleDivided.firstOrNull()?.firstOrNull()?.uppercase() ?: ""
            val lastLetter = if (titleDivided.size < 2) "" else titleDivided.lastOrNull()?.firstOrNull()?.uppercase() ?: ""
            return "$firstLetter$lastLetter"
        }
}

data class Genres(
    @SerializedName("primary")
    val primary: String?
)

data class Hub(
    @SerializedName("actions")
    val actions: List<Action>?,
    @SerializedName("displayname")
    val displayname: String?,
    @SerializedName("explicit")
    val explicit: Boolean?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("options")
    val options: List<Option>?,
    @SerializedName("type")
    val type: String?
)

data class Images(
    @SerializedName("background")
    val background: String?,
    @SerializedName("coverart")
    val coverart: String?,
    @SerializedName("coverarthq")
    val coverarthq: String?,
)

data class Section(
    @SerializedName("beacondata")
    val beacondata: BeacondataX?,
    @SerializedName("footer")
    val footer: String?,
    @SerializedName("metadata")
    val metadata: List<Metadata>?,
    @SerializedName("metapages")
    val metapages: List<Metapage>?,
    @SerializedName("tabname")
    val tabname: String?,
    @SerializedName("text")
    val text: List<String>?,
    @SerializedName("type")
    val type: String?
)

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

data class Urlparams(
    @SerializedName("{trackartist}")
    val trackartist: String?,
    @SerializedName("{tracktitle}")
    val tracktitle: String?
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

data class Option(
    @SerializedName("actions")
    val actions: List<ActionX>?,
    @SerializedName("beacondata")
    val beacondata: Beacondata?,
    @SerializedName("caption")
    val caption: String?,
    @SerializedName("colouroverflowimage")
    val colouroverflowimage: Boolean?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("listcaption")
    val listcaption: String?,
    @SerializedName("overflowimage")
    val overflowimage: String?,
    @SerializedName("providername")
    val providername: String?,
    @SerializedName("type")
    val type: String?
)

data class ActionX(
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)

data class Beacondata(
    @SerializedName("providername")
    val providername: String?,
    @SerializedName("type")
    val type: String?
)

data class BeacondataX(
    @SerializedName("commontrackid")
    val commontrackid: String?,
    @SerializedName("lyricsid")
    val lyricsid: String?,
    @SerializedName("providername")
    val providername: String?
)

data class Metadata(
    @SerializedName("text")
    val text: String?,
    @SerializedName("title")
    val title: String?
)

data class Metapage(
    @SerializedName("caption")
    val caption: String?,
    @SerializedName("image")
    val image: String?
)
