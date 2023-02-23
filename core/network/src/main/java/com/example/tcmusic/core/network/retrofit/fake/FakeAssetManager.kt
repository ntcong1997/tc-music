package com.example.tcmusic.core.network.retrofit.fake

import java.io.InputStream

fun interface FakeAssetManager {
    fun open(fileName: String): InputStream
}
