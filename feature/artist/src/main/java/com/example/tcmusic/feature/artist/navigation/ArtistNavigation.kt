package com.example.tcmusic.feature.artist.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.tcmusic.feature.artist.ArtistRoute

/**
 * Created by TC on 20/02/2023.
 */

const val artistRoute = "artist_route"

internal const val artistIdArg = "artistId"

fun NavController.navigateToArtist(artistId: String?, navOptions: NavOptions? = null) {
    this.navigate("$artistRoute/$artistId", navOptions)
}

fun NavGraphBuilder.artistScreen(
    onBackClick: () -> Unit,
    onPlayingMediaClick: (String?, String?) -> Unit
) {
    composable(
        route = "$artistRoute/{$artistIdArg}",
        arguments = listOf(
            navArgument(artistIdArg) {
                type = NavType.StringType
            }
        )
    ) {
        ArtistRoute(
            onBackClick = onBackClick,
            onPlayingMediaClick = onPlayingMediaClick
        )
    }
}
