package com.example.player.util

import android.support.v4.media.MediaMetadataCompat
import com.example.model.Track

/**
 * Created by TC on 14/12/2022.
 */

fun List<Track>.toMediaMetadata(): List<MediaMetadataCompat> {
    return map {
        MediaMetadataCompat.Builder()
            .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, it.id)
            .putString(MediaMetadataCompat.METADATA_KEY_TITLE, it.title)
            .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, it.uri)
            .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, it.subTitle)
            .putString(MediaMetadataCompat.METADATA_KEY_GENRE, it.genre)
            .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, it.image)
            .build()
    }
}
