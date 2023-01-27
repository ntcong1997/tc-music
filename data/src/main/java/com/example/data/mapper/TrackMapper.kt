package com.example.data.mapper

import com.example.data.entity.*
import com.example.model.*
import com.google.gson.Gson
import timber.log.Timber

/**
 * Created by TC on 09/01/2023.
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

fun TrackEntity.toTrack(): Track {
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

fun TrackV2Entity.toTrack(gson: Gson): Track {
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
