package com.example.tcmusic.core.testing.data

import com.example.tcmusic.core.model.PlayingMediaInfo

/**
 * Created by TC on 29/12/2022.
 */

val playingMediaInfoTestData1 = PlayingMediaInfo(
    id = "1",
    artist = null,
    coverArt = null,
    title = "Story of my life",
    version = null
)

val playingMediaInfoTestData2 = PlayingMediaInfo(
    id = "2",
    artist = null,
    coverArt = null,
    title = "Lost stars",
    version = null
)

val playingMediaInfoTestData3 = PlayingMediaInfo(
    id = "3",
    artist = null,
    coverArt = null,
    title = "It will rain",
    version = null
)

val playingMediasInfoTestData = listOf(
    playingMediaInfoTestData1,
    playingMediaInfoTestData2,
    playingMediaInfoTestData3
)
