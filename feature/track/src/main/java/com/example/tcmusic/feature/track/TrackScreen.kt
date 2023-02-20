package com.example.tcmusic.feature.track

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.tcmusic.core.ui.LoadingDialog
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by TC on 29/11/2022.
 */

@ExperimentalMaterialApi
@Composable
fun TrackRoute(
    onBackClick: () -> Unit,
    viewModel: TrackViewModel = hiltViewModel()
) {
    val trackUiState = viewModel.trackUiState.collectAsState()
    val trackDuration = viewModel.trackDuration.collectAsState()
    val trackProgress = viewModel.trackProgress.collectAsState()
    val trackIsPlaying = viewModel.trackIsPlaying.collectAsState()

    TrackScreen(
        trackUiState = trackUiState.value,
        trackDuration = trackDuration.value,
        trackProgress = trackProgress.value,
        trackIsPlaying = trackIsPlaying.value,
        onBackClick = onBackClick,
        onMoreClick = { },
        onProgressChange = viewModel::progressChange,
        onPlayClick = viewModel::clickPlay,
        onPauseClick = viewModel::clickPause,
        onSkipBackwardsClick = viewModel::clickSkipBackwards,
        onSkipForwardClick = viewModel::clickSkipForward
    )
}

@ExperimentalMaterialApi
@Composable
fun TrackScreen(
    trackUiState: TrackUiState,
    trackDuration: Long,
    trackProgress: Long,
    trackIsPlaying: Boolean,
    onBackClick: () -> Unit,
    onProgressChange: (Float) -> Unit,
    onMoreClick: () -> Unit,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onSkipBackwardsClick: () -> Unit,
    onSkipForwardClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val isTrackLoading = trackUiState is TrackUiState.Loading

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(
            onBackClick = onBackClick,
            onMoreClick = onMoreClick
        )

        when (trackUiState) {
            TrackUiState.Loading, TrackUiState.Error -> Unit
            is TrackUiState.Success -> {
                val track = trackUiState.track

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                ) {
                    TrackInfo(
                        image = track.image,
                        title = track.title,
                        subTitle = track.subTitle
                    )

                    Divider(color = GrayMercury, thickness = 1.dp, modifier = Modifier.padding(16.dp))

                    TrackPlayingBar(
                        duration = trackDuration,
                        progress = trackProgress,
                        onProgressChange = onProgressChange
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TrackAction(
                        isPlaying = trackIsPlaying,
                        onPlayClick = onPlayClick,
                        onPauseClick = onPauseClick,
                        onSkipBackwardsClick = onSkipBackwardsClick,
                        onSkipForwardClick = onSkipForwardClick
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.show()
                            }
                        }) {
                            Image(
                                painter = painterResource(id = TcMusicIcons.Up),
                                contentDescription = null
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.text_lyrics),
                            color = Black,
                            fontSize = 14.sp
                        )
                    }
                }

                TrackLyrics(
                    modalBottomSheetState = modalBottomSheetState,
                    lyrics = track.lyrics
                )
            }
        }
    }

    if (isTrackLoading) {
        LoadingDialog()
    }
}

@Composable
fun Header(
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Image(
                painter = painterResource(id = TcMusicIcons.LeftArrow),
                contentDescription = null
            )
        }

        IconButton(
            onClick = onMoreClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Image(painter = painterResource(id = TcMusicIcons.More), contentDescription = null)
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
        if (image.isNullOrBlank()) TrackCompactTitle(title = title)
        else TrackImage(image = image)

        Spacer(modifier = Modifier.height(16.dp))

        TrackTitle(title = title)

        Spacer(modifier = Modifier.height(10.dp))

        TrackSubTitle(subTitle = subTitle)
    }
}

@Composable
fun TrackCompactTitle(
    title: String?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .aspectRatio(1f)
            .background(BlueRibbon, RoundedCornerShape(10.dp))
    ) {
        Text(
            text = title.compactTo2Letters(),
            color = White,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun TrackImage(
    image: String?
) {
    AsyncImage(
        model = image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
fun TrackTitle(
    title: String?
) {
    Text(
        text = title.orEmpty(),
        color = Black,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TrackSubTitle(
    subTitle: String?
) {
    Text(
        text = subTitle.orEmpty(),
        color = Black,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TrackPlayingBar(
    duration: Long,
    progress: Long,
    onProgressChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TrackSlider(
            duration = duration,
            progress = progress,
            onProgressChange = {
                onProgressChange(it)
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            TrackProgress(progress = progress)

            TrackDuration(duration = duration)
        }
    }
}

@Composable
fun TrackSlider(
    duration: Long,
    progress: Long,
    onProgressChange: (Float) -> Unit
) {
    Slider(
        value = progress / 1000f,
        onValueChange = {
            onProgressChange(it)
        },
        valueRange = 0f..(duration / 1000f),
        steps = 1,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
}

@Composable
fun BoxScope.TrackProgress(
    progress: Long
) {
    Text(
        text = convertTimeInMillisToMinuteSecondFormat(progress),
        color = Black,
        fontSize = 14.sp,
        modifier = Modifier.align(Alignment.CenterStart)
    )
}

@Composable
fun BoxScope.TrackDuration(
    duration: Long
) {
    Text(
        text = convertTimeInMillisToMinuteSecondFormat(duration),
        color = Black,
        fontSize = 14.sp,
        modifier = Modifier.align(Alignment.CenterEnd)
    )
}

@Composable
fun TrackAction(
    isPlaying: Boolean,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onSkipBackwardsClick: () -> Unit,
    onSkipForwardClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = TcMusicIcons.Random),
                contentDescription = null
            )
        }

        IconButton(onClick = onSkipBackwardsClick) {
            Image(
                painter = painterResource(id = TcMusicIcons.Previous),
                contentDescription = null
            )
        }

        if (isPlaying) {
            Image(
                painter = painterResource(id = TcMusicIcons.Pause),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onPauseClick()
                }
            )
        } else {
            Image(
                painter = painterResource(id = TcMusicIcons.Play),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onPlayClick()
                }
            )
        }

        IconButton(onClick = onSkipForwardClick) {
            Image(
                painter = painterResource(id = TcMusicIcons.Next),
                contentDescription = null
            )
        }

        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = TcMusicIcons.Repeat),
                contentDescription = null
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TrackLyrics(
    modalBottomSheetState: ModalBottomSheetState,
    lyrics: List<String>
) {
    ModalBottomSheetLayout(
        sheetContent = {
            Text(
                text = stringResource(id = R.string.text_lyrics),
                color = White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(16.dp, 0.dp, 16.dp, 16.dp)
            ) {
                items(lyrics) { lyric ->
                    Text(
                        text = lyric,
                        color = White,
                        fontSize = 16.sp
                    )
                }
            }
        },
        sheetState = modalBottomSheetState,
        sheetBackgroundColor = BlueRoyal,
        sheetShape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp)
    ) {
    }
}

private fun convertTimeInMillisToMinuteSecondFormat(timeInMillis: Long): String {
    val minutes = ((timeInMillis / 1000) / 60).toInt()
    val seconds = ((timeInMillis / 1000) % 60).toInt()

    return String.format(Locale.getDefault(), "%d:%02d", minutes, seconds)
}

@ExperimentalMaterialApi
@Preview
@Composable
fun TrackScreenPreview() {
    TrackScreen(
        trackUiState = TrackUiState.Loading,
        trackDuration = 60000L,
        trackProgress = 20000L,
        trackIsPlaying = true,
        onBackClick = { },
        onProgressChange = { _ ->
        },
        onMoreClick = { },
        onPlayClick = { },
        onPauseClick = { },
        onSkipBackwardsClick = { },
        onSkipForwardClick = { }
    )
}
