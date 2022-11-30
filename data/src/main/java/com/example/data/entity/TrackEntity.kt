package com.example.data.entity
import com.google.gson.annotations.SerializedName


/**
 * Created by TC on 30/11/2022.
 */

data class TrackEntity(
    @SerializedName("albumadamid")
    val albumadamid: String?,
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
    @SerializedName("isrc")
    val isrc: String?,
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
    @SerializedName("trackadamid")
    val trackadamid: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlparams")
    val urlparams: Urlparams?
)

data class Artist(
    @SerializedName("adamid")
    val adamid: String?,
    @SerializedName("alias")
    val alias: String?,
    @SerializedName("id")
    val id: String?
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

data class Section(
    @SerializedName("metadata")
    val metadata: List<Metadata?>?,
    @SerializedName("metapages")
    val metapages: List<Metapage?>?,
    @SerializedName("tabname")
    val tabname: String?,
    @SerializedName("type")
    val type: String?
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