package com.example.tcmusic.feature.track.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

/**
 * Created by TC on 19/02/2023.
 */

const val trackRoute = "track_route"

internal const val trackIdArg = "trackId"
internal const val trackVersionArg = "trackVersion"

fun NavController.navigateToTrack(trackId: String, trackVersion: String) {
    this.navigate("$trackRoute/$trackId&$trackVersion")
}

fun NavGraphBuilder.trackScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "$trackRoute/{$trackIdArg}&{$trackVersionArg}",
        arguments = listOf(
            navArgument(trackIdArg) {
                type = NavType.StringType
            },
            navArgument(trackVersionArg) {
                type = NavType.StringType
            }
        )
    ) {

    }
}