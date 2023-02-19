package com.example.tcmusic.feature.track

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.model.Track
import com.example.tcmusic.R
import com.example.tcmusic.ui.theme.*
import com.example.tcmusic.util.compact
import com.example.tcmusic.util.convertTimeInMillisToMinuteSecondFormat
import com.example.test.data.Track_1
import kotlinx.coroutines.launch

/**
 * Created by TC on 29/11/2022.
 */

@ExperimentalMaterialApi
@Composable
fun TrackDetailScreen(
    trackId: String?,
    trackVersion: String?,
    navController: NavController,
    viewModel: TrackViewModel = hiltViewModel()
) {
    val track = viewModel.track.collectAsState()
    val trackDuration = viewModel.trackDuration.collectAsState()
    val trackProgress = viewModel.trackProgress.collectAsState()
    val trackIsPlaying = viewModel.trackIsPlaying.collectAsState()

    TrackDetailScreen(
        track = track.value,
        trackDuration = trackDuration.value,
        trackProgress = trackProgress.value,
        trackIsPlaying = trackIsPlaying.value,
        onClickBack = {
            navController.navigateUp()
        },
        onClickMore = { },
        onProgressChange = viewModel::progressChange,
        onClickPlay = viewModel::clickPlay,
        onClickPause = viewModel::clickPause,
        onClickSkipBackwards = viewModel::clickSkipBackwards,
        onClickSkipForward = viewModel::clickSkipForward
    )

    LaunchedEffect(key1 = trackId) {
        viewModel.track(trackId, trackVersion)
    }
}

@ExperimentalMaterialApi
@Composable
fun TrackDetailScreen(
    track: Track?,
    trackDuration: Long,
    trackProgress: Long,
    trackIsPlaying: Boolean,
    onClickBack: () -> Unit,
    onProgressChange: (Float) -> Unit,
    onClickMore: () -> Unit,
    onClickPlay: () -> Unit,
    onClickPause: () -> Unit,
    onClickSkipBackwards: () -> Unit,
    onClickSkipForward: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

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
                image = track?.image,
                title = track?.title,
                subTitle = track?.subTitle
            )

            Divider(color = GrayMercury, thickness = 1.dp, modifier = Modifier.padding(16.dp))

            TrackPlayingBar(
                duration = trackDuration,
                progress = trackProgress,
                onProgressChange = {
                    onProgressChange(it)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TrackAction(
                isPlaying = trackIsPlaying,
                onClickPlay = {
                    onClickPlay()
                },
                onClickPause = {
                    onClickPause()
                },
                onClickSkipBackwards = {
                    onClickSkipBackwards()
                },
                onClickSkipForward = {
                    onClickSkipForward()
                }
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
                        painter = painterResource(id = R.drawable.ic_up),
                        contentDescription = null
                    )
                }

                Text(
                    text = stringResource(id = R.string.track_detail_screen_text_lyrics),
                    color = Black,
                    fontSize = 14.sp
                )
            }
        }
    }

    TrackLyrics(
        modalBottomSheetState = modalBottomSheetState,
        lyrics = track?.lyrics ?: listOf()
    )
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
        if (image.isNullOrBlank())
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
                    .background(BlueRibbon, RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = title.compact(),
                    color = White,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        else
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
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
fun TrackPlayingBar(
    duration: Long,
    progress: Long,
    onProgressChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = convertTimeInMillisToMinuteSecondFormat(progress),
                color = Black,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Text(
                text = convertTimeInMillisToMinuteSecondFormat(duration),
                color = Black,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
fun TrackAction(
    isPlaying: Boolean,
    onClickPlay: () -> Unit,
    onClickPause: () -> Unit,
    onClickSkipBackwards: () -> Unit,
    onClickSkipForward: () -> Unit
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
                painter = painterResource(id = R.drawable.ic_random),
                contentDescription = null
            )
        }

        IconButton(onClick = {
            onClickSkipBackwards()
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_previous),
                contentDescription = null
            )
        }

        if (isPlaying) {
            Image(
                painter = painterResource(id = R.drawable.ic_pause),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onClickPause()
                }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onClickPlay()
                }
            )
        }

        IconButton(onClick = {
            onClickSkipForward()
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_next),
                contentDescription = null
            )
        }

        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.ic_repeat),
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
                text = stringResource(id = R.string.track_detail_screen_text_lyrics),
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

@ExperimentalMaterialApi
@Preview
@Composable
fun TrackDetailScreenPreview() {
    TrackDetailScreen(
        track = Track_1,
        trackDuration = 60000L,
        trackProgress = 20000L,
        trackIsPlaying = true,
        onClickBack = { },
        onProgressChange = { _ ->
        },
        onClickMore = { },
        onClickPlay = { },
        onClickPause = { },
        onClickSkipBackwards = { },
        onClickSkipForward = { }
    )
}
