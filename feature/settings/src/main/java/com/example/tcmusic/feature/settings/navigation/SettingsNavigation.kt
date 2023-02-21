package com.example.tcmusic.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tcmusic.feature.settings.SettingsRoute

/**
 * Created by TC on 21/02/2023.
 */

const val settingsRoute = "settings_route"

fun NavController.navigateToSettings() {
    this.navigate(settingsRoute)
}

fun NavGraphBuilder.settingsScreen(

) {
    composable(route = settingsRoute) {
        SettingsRoute()
    }
}