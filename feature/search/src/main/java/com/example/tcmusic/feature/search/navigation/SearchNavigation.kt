package com.example.tcmusic.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.tcmusic.feature.search.SearchRoute

/**
 * Created by TC on 20/02/2023.
 */

const val searchRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(
    onArtistClick: (String?) -> Unit
) {
    composable(route = searchRoute) {
        SearchRoute(
            onArtistClick = onArtistClick
        )
    }
}