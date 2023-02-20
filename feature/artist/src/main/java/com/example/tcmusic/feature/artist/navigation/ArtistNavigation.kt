package com.example.tcmusic.feature.artist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

/**
 * Created by TC on 20/02/2023.
 */

const val artistRoute = "artist_route"

internal const val artistIdArg = "artistId"

fun NavController.navigateToArtist(artistId: String) {
    this.navigate("$artistRoute/$artistId")
}

fun NavGraphBuilder.artistScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "$artistRoute/{$artistIdArg}",
        arguments = listOf(
            navArgument(artistIdArg) {
                type = NavType.StringType
            }
        )
    ) {

    }
}
