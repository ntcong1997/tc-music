package com.example.player.util

import android.graphics.Bitmap
import android.support.v4.media.MediaMetadataCompat

/**
 * Created by TC on 15/12/2022.
 */

fun addDurationToMediaMetadata(mediaMetadata: MediaMetadataCompat?, duration: Long): MediaMetadataCompat {
    return MediaMetadataCompat.Builder()
        .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID))
        .putString(MediaMetadataCompat.METADATA_KEY_TITLE, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_TITLE))
        .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI))
        .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_ARTIST))
        .putString(MediaMetadataCompat.METADATA_KEY_GENRE, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_GENRE))
        .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI))
        .putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, mediaMetadata?.getBitmap(MediaMetadataCompat.METADATA_KEY_ARTIST))
        .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, duration)
        .build()
}

fun addAlbumArtToMediaMetadata(mediaMetadata: MediaMetadataCompat?, albumArt: Bitmap?): MediaMetadataCompat {
    return MediaMetadataCompat.Builder()
        .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID))
        .putString(MediaMetadataCompat.METADATA_KEY_TITLE, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_TITLE))
        .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI))
        .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_ARTIST))
        .putString(MediaMetadataCompat.METADATA_KEY_GENRE, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_GENRE))
        .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, mediaMetadata?.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI))
        .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, mediaMetadata?.getLong(MediaMetadataCompat.METADATA_KEY_DURATION) ?: 0L)
        .putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, albumArt)
        .build()
}
