package com.example.tcmusic.core.player.util

import android.graphics.Bitmap
import android.support.v4.media.MediaMetadataCompat
import com.example.tcmusic.core.model.Track

/**
 * Created by TC on 15/02/2023.
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

fun addDurationToMediaMetadata(
    mediaMetadata: MediaMetadataCompat?,
    duration: Long
): MediaMetadataCompat {
    return MediaMetadataCompat.Builder()
        .putString(
            MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_MEDIA_ID
            )
        )
        .putString(
            MediaMetadataCompat.METADATA_KEY_TITLE, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_TITLE
            )
        )
        .putString(
            MediaMetadataCompat.METADATA_KEY_MEDIA_URI, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_MEDIA_URI
            )
        )
        .putString(
            MediaMetadataCompat.METADATA_KEY_ARTIST, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_ARTIST
            )
        )
        .putString(
            MediaMetadataCompat.METADATA_KEY_GENRE, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_GENRE
            )
        )
        .putString(
            MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI
            )
        )
        .putBitmap(
            MediaMetadataCompat.METADATA_KEY_ALBUM_ART, mediaMetadata?.getBitmap(
                MediaMetadataCompat.METADATA_KEY_ARTIST
            )
        )
        .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, duration)
        .build()
}

fun addAlbumArtToMediaMetadata(
    mediaMetadata: MediaMetadataCompat?,
    albumArt: Bitmap?
): MediaMetadataCompat {
    return MediaMetadataCompat.Builder()
        .putString(
            MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_MEDIA_ID
            )
        )
        .putString(
            MediaMetadataCompat.METADATA_KEY_TITLE, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_TITLE
            )
        )
        .putString(
            MediaMetadataCompat.METADATA_KEY_MEDIA_URI, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_MEDIA_URI
            )
        )
        .putString(
            MediaMetadataCompat.METADATA_KEY_ARTIST, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_ARTIST
            )
        )
        .putString(
            MediaMetadataCompat.METADATA_KEY_GENRE, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_GENRE
            )
        )
        .putString(
            MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, mediaMetadata?.getString(
                MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI
            )
        )
        .putLong(
            MediaMetadataCompat.METADATA_KEY_DURATION, mediaMetadata?.getLong(
                MediaMetadataCompat.METADATA_KEY_DURATION
            ) ?: 0L
        )
        .putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, albumArt)
        .build()
}