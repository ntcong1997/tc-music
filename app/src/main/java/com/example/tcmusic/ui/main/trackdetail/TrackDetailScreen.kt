package com.example.tcmusic.ui.main.trackdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.domain.model.Track
import com.example.tcmusic.R
import com.example.tcmusic.ui.theme.Black
import com.example.tcmusic.ui.theme.GrayMercury
import com.example.tcmusic.ui.theme.White

/**
 * Created by TC on 29/11/2022.
 */

@Composable
fun TrackDetailScreen(
    trackId: String?,
    navController: NavController,
    viewModel: TrackDetailViewModel = hiltViewModel()
) {
    val track = viewModel.track.collectAsState(initial = null)

    TrackDetailScreen(
        track = track.value,
        onClickBack = {
            navController.navigateUp()
        },
        onClickMore = { }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getTrackDetail(trackId)
    }
}

@Composable
fun TrackDetailScreen(
    track: Track?,
    onClickBack: () -> Unit,
    onClickMore: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Header(
            onClickBack = {
                onClickBack()
            },
            onClickMore = {
                onClickMore()
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            TrackInfo(
                image = track?.images?.coverart,
                title = track?.title,
                subTitle = track?.subtitle
            )

            Divider(color = GrayMercury, thickness = 1.dp, modifier = Modifier.padding(16.dp))

            TrackPlayingBar()

            Spacer(modifier = Modifier.height(16.dp))

            TrackAction()
        }
    }
}

@Composable
fun Header(
    onClickBack: () -> Unit,
    onClickMore: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = {
                onClickBack()
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_left_arrow),
                contentDescription = null
            )
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

@Composable
fun TrackInfo(
    image: String?,
    title: String?,
    subTitle: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title ?: "",
            color = Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = subTitle ?: "",
            color = Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TrackPlayingBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Slider(
            value = 5f,
            onValueChange = { },
            valueRange = 0f..100f,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "00:00",
                color = Black,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Text(
                text = "03:50",
                color = Black,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
fun TrackAction() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.ic_previous),
                contentDescription = null
            )
        }

        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.ic_rewind_10),
                contentDescription = null
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_play_2),
            contentDescription = null
        )

        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.ic_fast_forward_10),
                contentDescription = null
            )
        }

        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun TrackDetailScreenPreview() {
    TrackDetailScreen(
        track = Track(
            albumadamid = null,
            alias = null,
            artists = null,
            genres = null,
            hub = null,
            images = null,
            isrc = null,
            key = null,
            layout = null,
            releasedate = null,
            sections = null,
            share = null,
            subtitle = "Bruno Mars",
            title = "It will rain",
            trackadamid = null,
            type = null,
            url = null,
            urlparams = null
        ),
        onClickBack = { },
        onClickMore = { }
    )
}