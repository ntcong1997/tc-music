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
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.testing.data.artistTestData1

/**
 * Created by TC on 21/11/2022.
 */

const val ArtistCardContentDescriptionPrefix = "ArtistCard"
const val ArtistAvatarContentDescriptionPrefix = "ArtistAvatar"
const val ArtistMoreIconContentDescriptionPrefix = "ArtistMoreIcon"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArtistCard(
    artist: Artist,
    onClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                this.contentDescription = "$ArtistCardContentDescriptionPrefix-${artist.id}"
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp, 16.dp, 0.dp, 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!artist.avatar.isNullOrBlank()) ArtistAvatar(id = artist.id, avatar = artist.avatar)
            else ArtistCompactNameAvatar(name = artist.name)

            ArtistName(name = artist.name)

            IconButton(
                onClick = onMoreClick,
                modifier = Modifier.semantics {
                    this.contentDescription = "$ArtistMoreIconContentDescriptionPrefix-${artist.id}"
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
fun ArtistAvatar(
    id: String?,
    avatar: String?
) {
    AsyncImage(
        model = avatar,
        contentDescription = "$ArtistAvatarContentDescriptionPrefix-$id",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
    )
}

@Composable
fun ArtistCompactNameAvatar(
    name: String?
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(BlueRibbon, CircleShape)
    ) {
        Text(
            text = name.compactTo2Letters(),
            color = White,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun RowScope.ArtistName(
    name: String?
) {
    Text(
        text = name.orEmpty(),
        color = Black,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 10.dp)
    )
}

@Preview
@Composable
fun ArtistCardPreview() {
    ArtistCard(
        artist = artistTestData1,
        onClick = { },
        onMoreClick = { }
    )
}
