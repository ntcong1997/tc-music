package com.example.tcmusic.core.testing.data

import com.example.tcmusic.core.model.Artist

/**
 * Created by TC on 09/01/2023.
 */

val artistTestData1 = Artist(
    avatar = "https://cdn.britannica.com/30/164030-050-255C7C8E/One-Direction-Niall-Horan-Zayn-Malik-Liam-2011.jpg",
    id = "1",
    name = "One Direction",
    topSongs = listOf(
        trackTestData1,
        trackTestData11,
        trackTestData12
    )
)

val artistTestData2 = Artist(
    avatar = "https://m.media-amazon.com/images/M/MV5BMjE1NDE3ODA0MV5BMl5BanBnXkFtZTcwODQ5NTgwNQ@@._V1_.jpg",
    id = "2",
    name = "Bruno Mars",
    topSongs = listOf()
)

val artistTestData3 = Artist(
    avatar = "https://m.media-amazon.com/images/M/MV5BZGM0YjhkZmEtNGYxYy00OTk0LThlNDgtNGQzM2YwNjU0NDQzXkEyXkFqcGdeQXVyMTU3ODQxNDYz._V1_.jpg",
    id = "3",
    name = "Taylor Swift",
    topSongs = listOf()
)

val artistTestData4 = Artist(
    avatar = "https://m.media-amazon.com/images/M/MV5BMjI4NjU4MDEwN15BMl5BanBnXkFtZTcwNTQzNDMwNw@@._V1_.jpg",
    id = "4",
    name = "Adam Levine",
    topSongs = listOf()
)

val artistTestData5 = Artist(
    avatar = "https://variety.com/wp-content/uploads/2023/01/HR-GettyImages-1358350126-copy.jpg",
    id = "5",
    name = "Jonas Brothers",
    topSongs = listOf()
)

val artistTestData6 = Artist(
    avatar = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Ed_Sheeran-6886_%28cropped%29.jpg/1200px-Ed_Sheeran-6886_%28cropped%29.jpg",
    id = "6",
    name = "Ed Sheeran",
    topSongs = listOf()
)

val artistTestData7 = Artist(
    avatar = null,
    id = "7",
    name = "Avril Lavigne",
    topSongs = listOf()
)

val artistTestData8 = Artist(
    avatar = null,
    id = "8",
    name = "JLS",
    topSongs = listOf()
)

val artistTestData9 = Artist(
    avatar = null,
    id = "9",
    name = "John Legend",
    topSongs = listOf()
)

val artistTestData10 = Artist(
    avatar = null,
    id = "10",
    name = "Guns N'Roses",
    topSongs = listOf()
)

val artistsTestData = listOf(
    artistTestData1,
    artistTestData2,
    artistTestData3,
    artistTestData4,
    artistTestData5,
    artistTestData6,
    artistTestData7,
    artistTestData8,
    artistTestData9,
    artistTestData10
)
