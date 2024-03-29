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

/**
 * Created by TC on 21/11/2022.
 */

const val TrackCardContentDescriptionPrefix = "TrackCard"
const val TrackImageContentDescriptionPrefix = "TrackImage"
const val TrackMoreIconContentDescriptionPrefix = "TrackMoreIcon"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrackCard(
    track: Track,
    onClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                this.contentDescription = "$TrackCardContentDescriptionPrefix-${track.id}"
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp, 16.dp, 0.dp, 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!track.image.isNullOrBlank()) TrackImage(id = track.id, image = track.image)
            else TrackCompactTitleImage(title = track.title)

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                TrackTitle(title = track.title)

                Spacer(modifier = Modifier.height(8.dp))

                TrackSubTitle(subTitle = track.subTitle)
            }

            IconButton(
                onClick = onMoreClick,
                modifier = Modifier.semantics {
                   this.contentDescription = "$TrackMoreIconContentDescriptionPrefix-${track.id}"
                }
            ) {
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
    id: String?,
    image: String?
) {
    AsyncImage(
        model = image,
        contentDescription = "$TrackImageContentDescriptionPrefix-$id",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
    )
}

@Composable
fun TrackCompactTitleImage(
    title: String?
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(BlueRibbon, CircleShape)
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
        onClick = { },
        onMoreClick = { }
    )
}
