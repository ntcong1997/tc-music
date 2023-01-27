package com.example.tcmusic.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.model.Track
import com.example.tcmusic.R
import com.example.tcmusic.ui.theme.Black
import com.example.tcmusic.ui.theme.BlueRibbon
import com.example.tcmusic.ui.theme.White
import com.example.tcmusic.util.compact
import com.example.test.data.Track_1

/**
 * Created by TC on 21/11/2022.
 */

@Composable
fun TrackItem(
    track: Track,
    onClickTrack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickTrack()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp, 16.dp, 0.dp, 16.dp)
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!track.image.isNullOrBlank())
                AsyncImage(
                    model = track.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            else
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(BlueRibbon, CircleShape)
                ) {
                    Text(
                        text = track.title.compact(),
                        color = White,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = track.title ?: "",
                    color = Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = track.subTitle ?: "",
                    color = Black,
                    fontSize = 12.sp
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
fun TrackItemPreview() {
    TrackItem(
        track = Track_1,
        onClickTrack = { }
    )
}
