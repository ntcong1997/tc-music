package com.example.tcmusic.core.data.model

import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.network.model.NetworkTrackV1
import com.example.tcmusic.core.network.model.NetworkTrackV2
import com.example.tcmusic.core.network.model.TopSongsData

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

fun NetworkTrackV1.toTrack(): Track {
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

fun NetworkTrackV2.toTrack(): Track {
    val shazamSong = resources?.shazamSongs?.entries?.firstOrNull()?.value
    val lyric = resources?.lyrics?.entries?.firstOrNull()?.value

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
