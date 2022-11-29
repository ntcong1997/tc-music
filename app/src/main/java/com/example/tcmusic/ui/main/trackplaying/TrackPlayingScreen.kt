package com.example.tcmusic.ui.main.trackplaying

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.tcmusic.R

/**
 * Created by TC on 29/11/2022.
 */

@Composable
fun TrackPlayingScreen(
    navController: NavController
) {
    TrackPlayingScreen(
        onClickBack = {
            navController.navigateUp()
        },
        onClickMore = { }
    )
}

@Composable
fun TrackPlayingScreen(
    onClickBack: () -> Unit,
    onClickMore: () -> Unit
) {
    Header(
        onClickBack = {
            onClickBack()
        },
        onClickMore = {
            onClickMore()
        }
    )
}

@Composable
fun Header(
    onClickBack: () -> Unit,
    onClickMore: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            onClick = {
                onClickBack()
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_left_arrow), contentDescription = null)
        }

        IconButton(
            onClick = {
                onClickMore()
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_more), contentDescription = null)
        }
    }
}

@Preview
@Composable
fun TrackPlayingScreenPreview() {
    TrackPlayingScreen(
        onClickBack = { },
        onClickMore = { }
    )
}