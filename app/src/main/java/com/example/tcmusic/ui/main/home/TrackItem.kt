package com.example.tcmusic.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
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
import com.example.domain.model.Images
import com.example.domain.model.Track
import com.example.tcmusic.ui.theme.Black
import com.example.tcmusic.R
import timber.log.Timber

/**
 * Created by TC on 21/11/2022.
 */

@Composable
fun TrackItem(
    track: Track
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.Center)
        ) {
            AsyncImage(
                model = track.images?.coverArt,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = track.title ?: "",
                    color = Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = track.subtitle ?: "",
                    color = Black,
                    fontSize = 10.sp
                )
            }

            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun TrackItem() {
    TrackItem(
        track = Track(
            alias = null,
            artists = null,
            genres = null,
            hub = null,
            images = Images(
                background = null,
                coverArt = "",
                coverArtHq = null
            ),
            key = null,
            releaseDate = null,
            sections = null,
            subtitle = "Bruno Mars",
            title = "It will rain",
            type = null,
            url = null
        )
    )
}