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
import androidx.compose.ui.layout.ContentScale
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
import com.example.model.PlayingMediaInfo
import com.example.model.Track
import com.example.tcmusic.R
import com.example.tcmusic.ui.components.ErrorDialog
import com.example.tcmusic.ui.components.LoadingDialog
import com.example.tcmusic.ui.components.TrackItem
import com.example.tcmusic.ui.main.Screen
import com.example.tcmusic.ui.theme.*
import com.example.tcmusic.util.compact
import com.example.test.data.Artist_1
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
    val playingMediaInfo = viewModel.playingMediaInfo.collectAsState()
    val isPlaying = viewModel.isPlaying.collectAsState()
    val artist = viewModel.artist.collectAsState()
    val topSongs = viewModel.topSongs.collectAsState(initial = listOf())
    val showLoading = viewModel.showLoading.collectAsState()
    val showError = viewModel.showError.collectAsState()

    ArtistDetailScreen(
        playingMediaInfo = playingMediaInfo.value,
        isPlaying = isPlaying.value,
        artist = artist.value,
        topSongs = topSongs.value,
        showLoading = showLoading.value,
        showError = showError.value,
        onClickBack = {
            navController.navigateUp()
        },
        onClickPlay = viewModel::clickPlay,
        onClickTrack = viewModel::clickTrack,
        onClickPlayingMediaInfo = {
            navController.navigate(Screen.TrackDetailScreen.route + "?trackId=${it.id}&trackVersion=${it.version}")
        },
        onClickPlayingMediaInfoPlay = viewModel::clickPlayingMediaInfoPlay,
        onClickPlayingMediaInfoPause = viewModel::clickPlayingMediaInfoPause,
        onClickPlayingMediaInfoSkipBackwards = viewModel::clickPlayingMediaInfoSkipBackwards,
        onClickPlayingMediaInfoSkipForward = viewModel::clickPlayingMediaInfoSkipForward,
        onDismissError = viewModel::dismissError
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getArtistDetail(artistId)
    }
}

@Composable
fun ArtistDetailScreen(
    playingMediaInfo: PlayingMediaInfo?,
    isPlaying: Boolean,
    artist: Artist?,
    topSongs: List<Track>,
    showLoading: Boolean,
    showError: Error?,
    onClickBack: () -> Unit,
    onClickPlay: () -> Unit,
    onClickTrack: (Track) -> Unit,
    onClickPlayingMediaInfo: (PlayingMediaInfo) -> Unit,
    onClickPlayingMediaInfoPlay: () -> Unit,
    onClickPlayingMediaInfoPause: () -> Unit,
    onClickPlayingMediaInfoSkipBackwards: () -> Unit,
    onClickPlayingMediaInfoSkipForward: () -> Unit,
    onDismissError: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    avatar = artist?.avatar,
                    name = artist?.name
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

        if (playingMediaInfo != null) {
            PlayingMediaInfoBar(
                playingMediaInfo = playingMediaInfo,
                isPlaying = isPlaying,
                onClickPlayingMediaInfo = {
                    onClickPlayingMediaInfo(it)
                },
                onClickPlay = {
                    onClickPlayingMediaInfoPlay()
                },
                onClickPause = {
                    onClickPlayingMediaInfoPause()
                },
                onClickSkipBackwards = {
                    onClickPlayingMediaInfoSkipBackwards()
                },
                onClickSkipForward = {
                    onClickPlayingMediaInfoSkipForward()
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
                it.id ?: UUID.randomUUID().toString()
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

@Composable
fun BoxScope.PlayingMediaInfoBar(
    playingMediaInfo: PlayingMediaInfo,
    isPlaying: Boolean,
    onClickPlayingMediaInfo: (PlayingMediaInfo) -> Unit,
    onClickPlay: () -> Unit,
    onClickPause: () -> Unit,
    onClickSkipBackwards: () -> Unit,
    onClickSkipForward: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .align(Alignment.BottomCenter)
            .background(GraySilverChalice, RoundedCornerShape(10.dp))
            .clickable {
                onClickPlayingMediaInfo(playingMediaInfo)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = playingMediaInfo.coverArt,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(16.dp, 16.dp, 0.dp, 16.dp)
                .size(48.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text(
                text = playingMediaInfo.title ?: "",
                color = White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = playingMediaInfo.artist ?: "",
                color = White,
                fontSize = 12.sp
            )
        }

        Row(
            modifier = Modifier
                .padding(0.dp, 16.dp, 16.dp, 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_previous_2),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onClickSkipBackwards()
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            if (isPlaying) {
                Image(
                    painter = painterResource(id = R.drawable.ic_pause_2),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onClickPause()
                    }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_play_2),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onClickPlay()
                    }
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_next_2),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onClickSkipForward()
                }
            )
        }
    }
}

@Preview
@Composable
fun ArtistDetailScreenPreview() {
    ArtistDetailScreen(
        playingMediaInfo = null,
        isPlaying = true,
        artist = Artist_1,
        topSongs = listOf(),
        showLoading = false,
        showError = null,
        onClickBack = { },
        onClickPlay = { },
        onClickTrack = { _ ->
        },
        onClickPlayingMediaInfo = { _ ->
        },
        onClickPlayingMediaInfoPlay = { },
        onClickPlayingMediaInfoPause = { },
        onClickPlayingMediaInfoSkipBackwards = { },
        onClickPlayingMediaInfoSkipForward = { },
        onDismissError = { }
    )
}
