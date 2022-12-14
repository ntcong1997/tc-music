package com.example.player.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by TC on 14/12/2022.
 */

@Singleton
class ImageCache @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val cache = HashMap<String, Bitmap>()

    suspend fun loadAllImagesAsync(imagesUrl: List<String>) {
        imagesUrl.forEach { imageUrl ->
            loadImageAsync(imageUrl)?.let {
                if (!cache.containsKey(imageUrl)) cache[imageUrl] = it
            }
        }
    }

    private suspend fun loadImageAsync(imageUrl: String): Bitmap? {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false) // Disable hardware bitmaps.
            .build()

        val result = loader.execute(request)
        return if (result is SuccessResult) {
            (result.drawable as BitmapDrawable).bitmap
        } else null
    }

    fun getBitmap(imageUrl: String?): Bitmap? {
        return cache[imageUrl]
    }
}
