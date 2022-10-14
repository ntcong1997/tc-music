package com.example.domain.model

/**
 * Created by TC on 14/10/2022.
 */
data class Track(
    val alias: String?,
    val artists: List<Artist?>?,
    val genres: Genres?,
    val hub: Hub?,
    val images: Images?,
    val key: String?,
    val releaseDate: String?,
    val sections: List<Section?>?,
    val subtitle: String?,
    val title: String?,
    val type: String?,
    val url: String?
)

data class Artist(
    val alias: String?,
    val id: String?
)

data class Genres(
    val primary: String?
)

data class Hub(
    val actions: List<Action?>?,
    val image: String?
)

data class Images(
    val background: String?,
    val coverArt: String?,
    val coverArtHq: String?
)

data class Section(
    val text: List<String?>?,
    val type: String?
)

data class Action(
    val id: String?,
    val name: String?,
    val type: String?,
    val uri: String?
)