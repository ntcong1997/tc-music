package com.example.model.mapper

import com.example.model.*

/**
 * Created by TC on 09/01/2023.
 */

fun TopSongsData.toTrack(): Track {
    return Track(
        alias = null,
        artists = null,
        genres = Genres(
            primary = attributes?.genreNames?.firstOrNull()
        ),
        hub = Hub(
            actions = listOf(
                Action(
                    id = null,
                    name = null,
                    type = "uri",
                    uri = attributes?.previews?.firstOrNull()?.url
                )
            ),
            displayname = null,
            explicit = null,
            image = null,
            options = null,
            type = null
        ),
        images = Images(
            background = null,
            coverart = attributes?.artwork?.url,
            coverarthq = null
        ),
        key = id,
        layout = null,
        releasedate = attributes?.releaseDate,
        sections = null,
        share = null,
        subtitle = attributes?.artistName,
        title = attributes?.name,
        type = null,
        url = null,
        urlparams = null
    )
}