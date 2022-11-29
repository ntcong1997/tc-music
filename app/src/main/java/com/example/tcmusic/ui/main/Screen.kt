package com.example.tcmusic.ui.main

import androidx.annotation.StringRes
import com.example.tcmusic.R

/**
 * Created by TC on 10/10/2022.
 */
sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: Int) {
    object Home : Screen("home", R.string.main_activity_text_home, R.drawable.ic_home)
    object Search : Screen("search", R.string.main_activity_text_search, R.drawable.ic_search)
    object Favorites : Screen("favorites", R.string.main_activity_text_favorites, R.drawable.ic_favorites)
    object Playlists : Screen("playlists", R.string.main_activity_text_playlists, R.drawable.ic_playlists)
    object Settings : Screen("settings", R.string.main_activity_text_settings, R.drawable.ic_settings)
}