package com.example.tcmusic.feature.data.model

import com.example.tcmusic.feature.model.Artist
import com.example.tcmusic.feature.network.model.NetworkArtist

/**
 * Created by TC on 15/02/2023.
 */

fun NetworkArtist.toArtist(): Artist {
    return Artist(
        avatar = artistAvatar,
        id = id,
        name = artistName,
        topSongs = topSongs.map { it.toTrack() }
    )
}