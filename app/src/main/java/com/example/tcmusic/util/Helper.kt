package com.example.tcmusic.util

import java.util.*

/**
 * Created by TC on 15/12/2022.
 */

fun convertTimeInMillisToMinuteSecondFormat(timeInMillis: Long): String {
    val minutes = ((timeInMillis / 1000) / 60).toInt()
    val seconds = ((timeInMillis / 1000) % 60).toInt()

    return String.format(Locale.getDefault(), "%d:%02d", minutes, seconds)
}
