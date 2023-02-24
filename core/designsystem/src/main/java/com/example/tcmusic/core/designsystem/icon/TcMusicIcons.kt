package com.example.tcmusic.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.tcmusic.core.designsystem.R

/**
 * Created by TC on 16/02/2023.
 */

object TcMusicIcons {
    val App = R.drawable.ic_app
    val FastForward10 = R.drawable.ic_fast_forward_10
    val Home = R.drawable.ic_home
    val LeftArrow = R.drawable.ic_left_arrow
    val More = R.drawable.ic_more
    val Next = R.drawable.ic_next
    val Next2 = R.drawable.ic_next_2
    val Pause = R.drawable.ic_pause
    val Pause2 = R.drawable.ic_pause_2
    val Play = R.drawable.ic_play
    val Play2 = R.drawable.ic_play_2
    val Play3 = R.drawable.ic_play_3
    val Playlists = R.drawable.ic_playlists
    val Previous = R.drawable.ic_previous
    val Previous2 = R.drawable.ic_previous_2
    val Random = R.drawable.ic_random
    val Repeat = R.drawable.ic_repeat
    val Rewind10 = R.drawable.ic_rewind_10
    val Search = R.drawable.ic_search
    val Search2 = Icons.Filled.Search
    val Settings = R.drawable.ic_settings
    val Up = R.drawable.ic_up
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
