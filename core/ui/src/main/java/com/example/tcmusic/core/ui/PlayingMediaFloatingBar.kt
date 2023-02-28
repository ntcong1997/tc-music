package com.example.tcmusic.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
import com.example.tcmusic.core.model.PlayingMedia
import com.example.tcmusic.core.testing.data.playingMediaTestData1
import com.example.tcmusic.core.ui.util.PlayingMediaCompactTitleImageContentDescription
import com.example.tcmusic.core.ui.util.PlayingMediaImageContentDescription
import com.example.tcmusic.core.ui.util.PlayingMediaPauseContentDescription
import com.example.tcmusic.core.ui.util.PlayingMediaPlayContentDescription

/**
 * Created by TC on 19/02/2023.
 */

@Composable
fun BoxScope.PlayingMediaFloatingBar(
    playingMedia: PlayingMedia,
    isPlaying: Boolean,
    onPlayingMediaClick: (PlayingMedia) -> Unit,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit,
    onSkipBackwardsClick: () -> Unit,
    onSkipForwardClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .align(Alignment.BottomCenter)
            .background(GraySilverChalice, RoundedCornerShape(10.dp))
            .clickable {
                onPlayingMediaClick(playingMedia)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!playingMedia.image.isNullOrBlank()) PlayingMediaImage(image = playingMedia.image)
        else PlayingMediaCompactTitleImage(title = playingMedia.title)

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            PlayingMediaTitle(title = playingMedia.title)

            Spacer(modifier = Modifier.height(4.dp))

            PlayingMediaArtist(artist = playingMedia.artist)
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
                    onSkipBackwardsClick()
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            PlayingMediaPlayPause(
                isPlaying = isPlaying,
                onPlayClick = onPlayClick,
                onPauseClick = onPauseClick
            )

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painterResource(id = TcMusicIcons.Next2),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onSkipForwardClick()
                }
            )
        }
    }
}

@Composable
fun PlayingMediaImage(
    image: String?
) {
    AsyncImage(
        model = image,
        contentDescription = PlayingMediaImageContentDescription,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .padding(16.dp, 16.dp, 0.dp, 16.dp)
            .size(48.dp)
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
fun PlayingMediaCompactTitleImage(
    title: String?
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .padding(16.dp, 16.dp, 0.dp, 16.dp)
            .background(BlueRibbon, RoundedCornerShape(10.dp))
            .semantics {
                this.contentDescription = PlayingMediaCompactTitleImageContentDescription
            }
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
fun PlayingMediaTitle(
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
fun PlayingMediaArtist(
    artist: String?
) {
    Text(
        text = artist.orEmpty(),
        color = White,
        fontSize = 12.sp
    )
}

@Composable
fun PlayingMediaPlayPause(
    isPlaying: Boolean,
    onPlayClick: () -> Unit,
    onPauseClick: () -> Unit
) {
    if (isPlaying) {
        Image(
            painter = painterResource(id = TcMusicIcons.Pause2),
            contentDescription = PlayingMediaPauseContentDescription,
            modifier = Modifier.clickable {
                onPauseClick()
            }
        )
    } else {
        Image(
            painter = painterResource(id = TcMusicIcons.Play2),
            contentDescription = PlayingMediaPlayContentDescription,
            modifier = Modifier.clickable {
                onPlayClick()
            }
        )
    }
}

@Preview
@Composable
fun BoxScope.PlayingMediaFloatingBarPreview() {
    PlayingMediaFloatingBar(
        playingMedia = playingMediaTestData1,
        isPlaying = false,
        onPlayingMediaClick = { _ ->
        },
        onPlayClick = { },
        onPauseClick = { },
        onSkipBackwardsClick = { },
        onSkipForwardClick = { }
    )
}
