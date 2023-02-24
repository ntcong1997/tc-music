package com.example.tcmusic.feature.artist

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
import coil.compose.AsyncImage
import com.example.tcmusic.core.common.util.compactTo2Letters
import com.example.tcmusic.core.designsystem.icon.TcMusicIcons
import com.example.tcmusic.core.designsystem.theme.*
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.ui.LoadingDialog
import com.example.tcmusic.core.ui.TrackCard
import java.util.*

/**
 * Created by TC on 20/02/2023.
 */

@Composable
fun ArtistRoute(
    onBackClick: () -> Unit,
    viewModel: ArtistViewModel = hiltViewModel()
) {
    val artistUiState = viewModel.artistUiState.collectAsState()

    ArtistScreen(
        artistUiState = artistUiState.value,
        onBackClick = onBackClick,
        onPlayClick = viewModel::clickPlay,
        onTrackClick = viewModel::clickTrack
    )
}

@Composable
fun ArtistScreen(
    artistUiState: ArtistUiState,
    onBackClick: () -> Unit,
    onPlayClick: (List<Track>) -> Unit,
    onTrackClick: (Track) -> Unit
) {
    val isArtistLoading = artistUiState is ArtistUiState.Loading

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
                onBackClick = onBackClick
            )

            if (artistUiState is ArtistUiState.Success) {
                val artist = artistUiState.artist

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    ArtistInfo(
                        avatar = artist.avatar,
                        name = artist.name
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(BlueRoyal, RoundedCornerShape(30.dp))
                            .clickable {
                                onPlayClick(artist.topSongs)
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = TcMusicIcons.Play3),
                            contentDescription = null,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = stringResource(id = R.string.action_play),
                            color = White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }

                    Divider(color = GrayMercury, thickness = 1.dp, modifier = Modifier.padding(16.dp))

                    TopSongs(
                        tracks = artist.topSongs,
                        onTrackClick = onTrackClick
                    )
                }
            }
        }
    }

    if (isArtistLoading) {
        LoadingDialog()
    }
}

@Composable
fun Header(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = {
                onBackClick()
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Image(
                painter = painterResource(id = TcMusicIcons.LeftArrow),
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
        if (avatar.isNullOrBlank()) ArtistAvatarCompactName(name = name)
        else ArtistAvatar(avatar = avatar)

        Spacer(modifier = Modifier.height(16.dp))

        ArtistName(name = name)
    }
}

@Composable
fun ArtistAvatar(
    avatar: String?
) {
    AsyncImage(
        model = avatar,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
fun ArtistAvatarCompactName(
    name: String?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .aspectRatio(1f)
            .background(BlueRibbon, RoundedCornerShape(10.dp))
    ) {
        Text(
            text = name.compactTo2Letters(),
            color = White,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ArtistName(
    name: String?
) {
    Text(
        text = name.orEmpty(),
        color = Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TopSongs(
    tracks: List<Track>,
    onTrackClick: (Track) -> Unit
) {
    Text(
        text = stringResource(id = R.string.text_top_songs),
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
            TrackCard(
                track = itemTrack,
                onClick = {
                    onTrackClick(itemTrack)
                }
            )
        }
    }
}

@Preview
@Composable
fun ArtistScreenPreview() {
    ArtistScreen(
        artistUiState = ArtistUiState.Loading,
        onBackClick = { },
        onPlayClick = { },
        onTrackClick = { _ ->
        }
    )
}
