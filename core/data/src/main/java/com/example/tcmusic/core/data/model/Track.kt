package com.example.tcmusic.core.data.model

import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.network.model.*
import com.google.gson.Gson

/**
 * Created by TC on 15/02/2023.
 */

fun TopSongsData.toTrack(): Track {
    return Track(
        id = id,
        image = attributes?.artwork?.url,
        genre = attributes?.genreNames?.firstOrNull(),
        lyrics = listOf(),
        title = attributes?.name,
        subTitle = attributes?.artistName,
        uri = attributes?.previews?.firstOrNull()?.url,
        version = "2"
    )
}

fun NetworkTrack.toTrack(): Track {
    return Track(
        id = key,
        image = images?.coverart,
        genre = genres?.primary,
        lyrics = lyrics,
        title = title,
        subTitle = subtitle,
        uri = mediaUri,
        version = "1"
    )
}

fun NetworkTrackV2.toTrack(gson: Gson): Track {
    val shazamSong = gson.fromJson(gson.toJson(resources?.shazamSongs?.entries?.firstOrNull()?.value), ShazamSong::class.java)
    val lyric = gson.fromJson(gson.toJson(resources?.lyrics?.entries?.firstOrNull()?.value), Lyric::class.java)

    return Track(
        id = shazamSong?.id,
        image = shazamSong?.attributes?.images?.coverArt,
        genre = shazamSong?.attributes?.genres?.primary,
        lyrics = lyric?.attributes?.text?.filterNotNull() ?: listOf(),
        title = shazamSong?.attributes?.title,
        subTitle = shazamSong?.attributes?.artist,
        uri = shazamSong?.attributes?.streaming?.preview,
        version = "2"
    )
}