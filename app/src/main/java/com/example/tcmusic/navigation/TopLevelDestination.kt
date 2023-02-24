package com.example.tcmusic.navigation

import com.example.tcmusic.core.designsystem.icon.Icon
import com.example.tcmusic.core.designsystem.icon.TcMusicIcons
import com.example.tcmusic.feature.home.R as homeR
import com.example.tcmusic.feature.playlists.R as playlistsR
import com.example.tcmusic.feature.search.R as searchR
import com.example.tcmusic.feature.settings.R as settingsR

/**
 * Created by TC on 21/02/2023.
 */

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens. Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */

sealed class TopLevelDestination(val name: String) {
    class Home(
        val icon: Icon = Icon.DrawableResourceIcon(TcMusicIcons.Home),
        val iconTextId: Int = homeR.string.text_home
    ) : TopLevelDestination("Home")

    class Search(
        val icon: Icon = Icon.DrawableResourceIcon(TcMusicIcons.Search),
        val iconTextId: Int = searchR.string.text_search
    ) : TopLevelDestination("Search")

    class Playlists(
        val icon: Icon = Icon.DrawableResourceIcon(TcMusicIcons.Playlists),
        val iconTextId: Int = playlistsR.string.text_playlists
    ) : TopLevelDestination("Playlists")

    class Settings(
        val icon: Icon = Icon.DrawableResourceIcon(TcMusicIcons.Settings),
        val iconTextId: Int = settingsR.string.text_settings
    ) : TopLevelDestination("Settings")

    object Artist : TopLevelDestination("Artist")

    object Track : TopLevelDestination("Artist")
}
