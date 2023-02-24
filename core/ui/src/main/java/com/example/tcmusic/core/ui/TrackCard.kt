package com.example.tcmusic.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tcmusic.core.common.util.compactTo2Letters
import com.example.tcmusic.core.designsystem.icon.TcMusicIcons
import com.example.tcmusic.core.designsystem.theme.Black
import com.example.tcmusic.core.designsystem.theme.BlueRibbon
import com.example.tcmusic.core.designsystem.theme.White
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.testing.data.trackTestData1
import com.example.tcmusic.core.ui.util.TrackCompactTitleContentDescription
import com.example.tcmusic.core.ui.util.TrackImageContentDescription

/**
 * Created by TC on 21/11/2022.
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrackCard(
    track: Track,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp, 16.dp, 0.dp, 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!track.image.isNullOrBlank()) TrackImage(image = track.image)
            else TrackCompactTitle(title = track.title)

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                TrackTitle(title = track.title)

                Spacer(modifier = Modifier.height(8.dp))

                TrackSubTitle(subTitle = track.subTitle)
            }

            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(id = TcMusicIcons.More),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun TrackImage(
    image: String?
) {
    AsyncImage(
        model = image,
        contentDescription = TrackImageContentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
    )
}

@Composable
fun TrackCompactTitle(
    title: String?
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(BlueRibbon, CircleShape)
            .semantics {
                this.contentDescription = TrackCompactTitleContentDescription
            }
    ) {
        Text(
            text = title.compactTo2Letters(),
            color = White,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun TrackTitle(
    title: String?
) {
    Text(
        text = title.orEmpty(),
        color = Black,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TrackSubTitle(
    subTitle: String?
) {
    Text(
        text = subTitle.orEmpty(),
        color = Black,
        fontSize = 12.sp
    )
}

@Preview
@Composable
fun TrackCardPreview() {
    TrackCard(
        track = trackTestData1,
        onClick = { }
    )
}
