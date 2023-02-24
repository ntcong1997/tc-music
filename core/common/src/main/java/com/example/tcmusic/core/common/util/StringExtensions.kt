package com.example.tcmusic.core.common.util

/**
 * Created by TC on 16/02/2023.
 */

fun String?.compactTo2Letters(): String {
    val stringDivided = this?.split(" ") ?: listOf()
    val firstLetter = stringDivided.firstOrNull()?.firstOrNull()?.uppercase().orEmpty()
    val lastLetter = if (stringDivided.size < 2) "" else stringDivided.lastOrNull()?.firstOrNull()?.uppercase().orEmpty()
    return "$firstLetter$lastLetter"
}
