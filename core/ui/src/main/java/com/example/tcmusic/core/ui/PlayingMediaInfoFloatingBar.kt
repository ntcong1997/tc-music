package com.example.tcmusic.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tcmusic.core.common.util.compactTo2Letters
import com.example.tcmusic.core.designsystem.icon.TcMusicIcons
import com.example.tcmusic.core.designsystem.theme.BlueRibbon
import com.example.tcmusic.core.designsystem.theme.GraySilverChalice
import com.example.tcmusic.core.designsystem.theme.White
import com.example.tcmusic.core.model.PlayingMediaInfo
import com.example.tcmusic.core.testing.data.playingMediaInfoTestData1

/**
 * Created by TC on 19/02/2023.
 */

@Composable
fun BoxScope.PlayingMediaInfoFloatingBar(
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
        if (playingMediaInfo.image.isNullOrBlank()) PlayingMediaInfoImage(image = playingMediaInfo.image)
        else PlayingMediaInfoCompactTitle(title = playingMediaInfo.title)

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            PlayingMediaInfoTitle(title = playingMediaInfo.title)

            Spacer(modifier = Modifier.height(4.dp))

            PlayingMediaInfoArtist(artist = playingMediaInfo.artist)
        }

        Row(
            modifier = Modifier
                .padding(0.dp, 16.dp, 16.dp, 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = TcMusicIcons.Previous2),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onClickSkipBackwards()
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            if (isPlaying) {
                Image(
                    painter = painterResource(id = TcMusicIcons.Pause2),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onClickPause()
                    }
                )
            } else {
                Image(
                    painter = painterResource(id = TcMusicIcons.Play2),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onClickPlay()
                    }
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = TcMusicIcons.Next2),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onClickSkipForward()
                }
            )
        }
    }
}

@Composable
fun PlayingMediaInfoImage(
    image: String?
) {
    AsyncImage(
        model = image,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .padding(16.dp, 16.dp, 0.dp, 16.dp)
            .size(48.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
fun PlayingMediaInfoCompactTitle(
    title: String?
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(BlueRibbon, CircleShape)
    ) {
        Text(
            text = title.compactTo2Letters(),
            color = White,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun PlayingMediaInfoTitle(
    title: String?
) {
    Text(
        text = title.orEmpty(),
        color = White,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun PlayingMediaInfoArtist(
    artist: String?
) {
    Text(
        text = artist.orEmpty(),
        color = White,
        fontSize = 12.sp
    )
}

@Preview
@Composable
fun BoxScope.PlayingMediaInfoFloatingBarPreview() {
    PlayingMediaInfoFloatingBar(
        playingMediaInfo = playingMediaInfoTestData1,
        isPlaying = false,
        onClickPlayingMediaInfo = { _ ->

        },
        onClickPlay = { },
        onClickPause = { },
        onClickSkipBackwards = { },
        onClickSkipForward = { })
}