package com.example.tcmusic.core.player.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

/**
 * Created by TC on 14/12/2022.
 */

suspend fun loadImageAsync(context: Context, imageUrl: String?): Bitmap? {
    return if (imageUrl != null) {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false) // Disable hardware bitmaps.
            .build()

        val result = loader.execute(request)
        if (result is SuccessResult) {
            (result.drawable as BitmapDrawable).bitmap
        } else null
    } else null
}
