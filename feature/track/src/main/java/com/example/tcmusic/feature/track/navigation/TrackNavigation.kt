package com.example.tcmusic.feature.track.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.tcmusic.feature.track.TrackRoute

/**
 * Created by TC on 19/02/2023.
 */

const val trackRoute = "track_route"

internal const val trackIdArg = "trackId"
internal const val trackVersionArg = "trackVersion"

fun NavController.navigateToTrack(trackId: String?, trackVersion: String?, navOptions: NavOptions? = null) {
    this.navigate("$trackRoute/$trackId&$trackVersion", navOptions)
}

@OptIn(ExperimentalMaterialApi::class)
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
        TrackRoute(onBackClick = onBackClick)
    }
}