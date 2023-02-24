package com.example.tcmusic.core.data.model

import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.network.model.NetworkArtist

/**
 * Created by TC on 15/02/2023.
 */

fun NetworkArtist.toArtist(): Artist {
    return Artist(
        avatar = artistAvatar,
        id = artistId,
        name = artistName,
        topSongs = topSongs.map { it.toTrack() }
    )
}
