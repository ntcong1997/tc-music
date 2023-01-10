package com.example.tcmusic.ui.main.artistdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.model.Artist
import com.example.model.Track
import com.example.tcmusic.R
import com.example.tcmusic.ui.components.ErrorDialog
import com.example.tcmusic.ui.components.LoadingDialog
import com.example.tcmusic.ui.components.TrackItem
import com.example.tcmusic.ui.theme.*
import com.example.tcmusic.util.compact
import java.util.*

/**
 * Created by TC on 09/01/2023.
 */

@Composable
fun ArtistDetailScreen(
    artistId: String,
    navController: NavController,
    viewModel: ArtistDetailViewModel = hiltViewModel()
) {
    val artist = viewModel.artist.collectAsState()
    val topSongs = viewModel.topSongs.collectAsState(initial = listOf())
    val showLoading = viewModel.showLoading.collectAsState()
    val showError = viewModel.showError.collectAsState()

    ArtistDetailScreen(
        artist = artist.value,
        topSongs = topSongs.value,
        showLoading = showLoading.value,
        showError = showError.value,
        onClickBack = {
            navController.navigateUp()
        },
        onClickPlay = viewModel::clickPlay,
        onClickTrack = viewModel::clickTrack,
        onDismissError = viewModel::dismissError
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getArtistDetail(artistId)
    }
}

@Composable
fun ArtistDetailScreen(
    artist: Artist?,
    topSongs: List<Track>,
    showLoading: Boolean,
    showError: Error?,
    onClickBack: () -> Unit,
    onClickPlay: () -> Unit,
    onClickTrack: (Track) -> Unit,
    onDismissError: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Header(
            onClickBack = {
                onClickBack()
            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            ArtistInfo(
                avatar = artist?.artistAvatar,
                name = artist?.artistName
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(BlueRoyal, RoundedCornerShape(30.dp))
                    .clickable {
                        onClickPlay()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_play_3),
                    contentDescription = null,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(id = R.string.artist_detail_screen_action_play),
                    color = White,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            Divider(color = GrayMercury, thickness = 1.dp, modifier = Modifier.padding(16.dp))

            TopSongs(
                tracks = topSongs,
                onClickTrack = {
                    onClickTrack(it)
                }
            )
        }
    }

    if (showLoading) {
        LoadingDialog()
    }

    when (showError) {
        Error.NETWORK -> ErrorDialog(error = stringResource(id = R.string.error_network)) { onDismissError() }
        Error.UNKNOWN -> ErrorDialog(error = stringResource(id = R.string.error_unknown)) { onDismissError() }
        else -> {}
    }
}

@Composable
fun Header(
    onClickBack: () -> Unit
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
    }
}

@Composable
fun ArtistInfo(
    avatar: String?,
    name: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (avatar.isNullOrBlank())
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
                    .background(BlueRibbon, RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = name.compact(),
                    color = White,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        else
            AsyncImage(
                model = avatar,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(10.dp))
            )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = name ?: "",
            color = Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TopSongs(
    tracks: List<Track>,
    onClickTrack: (Track) -> Unit
) {
    Text(
        text = stringResource(id = R.string.artist_detail_screen_text_top_songs),
        color = Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn {
        items(
            items = tracks,
            key = {
                it.key ?: UUID.randomUUID().toString()
            }
        ) { itemTrack ->
            TrackItem(
                track = itemTrack,
                onClickTrack = {
                    onClickTrack(itemTrack)
                }
            )
        }
    }
}

@Preview
@Composable
fun ArtistDetailScreenPreview() {
    ArtistDetailScreen(
        artist = Artist(
            adamid = "1",
            alias = null,
            avatar = null,
            data = null,
            name = "Bruno Mars"
        ),
        topSongs = listOf<Track>(),
        showLoading = false,
        showError = null,
        onClickBack = { },
        onClickPlay = { },
        onClickTrack = { _ ->
        },
        onDismissError = { }
    )
}
