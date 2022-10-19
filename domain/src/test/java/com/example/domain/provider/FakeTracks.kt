package com.example.domain.provider

import com.example.domain.model.*

/**
 * Created by TC on 19/10/2022.
 */

val Track_1 = Track(
    alias = "1",
    artists = listOf(),
    genres = Genres("pop"),
    hub = Hub(
        actions = listOf(
            Action(
                id = "123",
                name = "apple",
                type = null,
                uri = "https://..."
            )
        ),
        image = null
    ),
    images = Images(
        background = null,
        coverArt = null,
        coverArtHq = null
    ),
    key = null,
    releaseDate = null,
    sections = listOf(
        Section(
            text = listOf("A", "B", "C"),
            type = "lyrics"
        )
    ),
    subtitle = "One Direction",
    title = "Story of my life",
    type = "MUSIC",
    url = "https://..."
)

val Track_2 = Track(
    alias = "2",
    artists = listOf(),
    genres = Genres("pop"),
    hub = Hub(
        actions = listOf(
            Action(
                id = "123",
                name = "apple",
                type = null,
                uri = "https://..."
            )
        ),
        image = null
    ),
    images = Images(
        background = null,
        coverArt = null,
        coverArtHq = null
    ),
    key = null,
    releaseDate = null,
    sections = listOf(
        Section(
            text = listOf("A", "B", "C"),
            type = "lyrics"
        )
    ),
    subtitle = "Adam Levine",
    title = "Lost stars",
    type = "MUSIC",
    url = "https://..."
)

val Track_3 = Track(
    alias = "3",
    artists = listOf(),
    genres = Genres("pop"),
    hub = Hub(
        actions = listOf(
            Action(
                id = "123",
                name = "apple",
                type = null,
                uri = "https://..."
            )
        ),
        image = null
    ),
    images = Images(
        background = null,
        coverArt = null,
        coverArtHq = null
    ),
    key = null,
    releaseDate = null,
    sections = listOf(
        Section(
            text = listOf("A", "B", "C"),
            type = "lyrics"
        )
    ),
    subtitle = "Bruno Mars",
    title = "It will rain",
    type = "MUSIC",
    url = "https://..."
)