package com.example.data.mapper

import com.example.data.entity.ArtistEntity
import com.example.model.Artist

/**
 * Created by TC on 27/01/2023.
 */

fun ArtistEntity.toArtist(): Artist {
    return Artist(
        avatar = artistAvatar,
        id = id,
        name = artistName,
        topSongs = topSongs.map { it.toTrack() }
    )
}
