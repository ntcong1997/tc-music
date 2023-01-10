package com.example.tcmusic.util

/**
 * Created by TC on 10/01/2023.
 */

fun String?.compact(): String {
    val stringDivided = this?.split(" ") ?: listOf()
    val firstLetter = stringDivided.firstOrNull()?.firstOrNull()?.uppercase() ?: ""
    val lastLetter = if (stringDivided.size < 2) "" else stringDivided.lastOrNull()?.firstOrNull()?.uppercase() ?: ""
    return "$firstLetter$lastLetter"
}
