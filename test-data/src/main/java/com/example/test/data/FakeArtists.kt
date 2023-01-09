package com.example.test.data

import com.example.model.Artist
import com.example.model.Attributes
import com.example.model.Data

/**
 * Created by TC on 09/01/2023.
 */

val Artist_1 = Artist(
    adamid = "1",
    alias = null,
    avatar = null,
    data = null,
    name = "Bruno Mars"
)

val Artist_2 = Artist(
    adamid = "2",
    alias = null,
    avatar = null,
    data = listOf(
        Data(
            attributes = Attributes(
                artistBio = null,
                artwork = null,
                genreNames = null,
                name = "One Direction",
                origin = null,
                url = null
            ),
            href = null,
            id = "2",
            relationships = null,
            type = null,
            views = null
        )
    ),
    name = null
)

val Artist_3 = Artist(
    adamid = "3",
    alias = null,
    avatar = null,
    data = null,
    name = "Taylor Swift"
)

val Artist_4 = Artist(
    adamid = "4",
    alias = null,
    avatar = null,
    data = listOf(
        Data(
            attributes = Attributes(
                artistBio = null,
                artwork = null,
                genreNames = null,
                name = "Adam Levine",
                origin = null,
                url = null
            ),
            href = null,
            id = "4",
            relationships = null,
            type = null,
            views = null
        )
    ),
    name = null
)

val Artist_5 = Artist(
    adamid = "5",
    alias = null,
    avatar = null,
    data = listOf(
        Data(
            attributes = Attributes(
                artistBio = null,
                artwork = null,
                genreNames = null,
                name = "Adele",
                origin = null,
                url = null
            ),
            href = null,
            id = "5",
            relationships = null,
            type = null,
            views = null
        )
    ),
    name = null
)

val Artist_6 = Artist(
    adamid = "6",
    alias = null,
    avatar = null,
    data = null,
    name = "Ed Sheeran"
)

val Artist_7 = Artist(
    adamid = "7",
    alias = null,
    avatar = null,
    data = null,
    name = "Charlie Puth"
)

val Artist_8 = Artist(
    adamid = "8",
    alias = null,
    avatar = null,
    data = null,
    name = "Sia"
)

val Artist_9 = Artist(
    adamid = "9",
    alias = null,
    avatar = null,
    data = listOf(
        Data(
            attributes = Attributes(
                artistBio = null,
                artwork = null,
                genreNames = null,
                name = "Pitbull",
                origin = null,
                url = null
            ),
            href = null,
            id = "9",
            relationships = null,
            type = null,
            views = null
        )
    ),
    name = null
)

val Artist_10 = Artist(
    adamid = "10",
    alias = null,
    avatar = null,
    data = listOf(
        Data(
            attributes = Attributes(
                artistBio = null,
                artwork = null,
                genreNames = null,
                name = "Alan Walker",
                origin = null,
                url = null
            ),
            href = null,
            id = "10",
            relationships = null,
            type = null,
            views = null
        )
    ),
    name = null
)

val Artists = listOf(
    Artist_1,
    Artist_2,
    Artist_3,
    Artist_4,
    Artist_5,
    Artist_6,
    Artist_7,
    Artist_8,
    Artist_9,
    Artist_10
)
