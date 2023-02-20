package com.example.tcmusic.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tcmusic.feature.home.HomeRoute

/**
 * Created by TC on 20/02/2023.
 */

const val homeRoute = "home_route"

fun NavController.navigateToHome() {
    this.navigate(homeRoute)
}

fun NavGraphBuilder.homeScreen(

) {
    composable(route = homeRoute) {
        HomeRoute()
    }
}