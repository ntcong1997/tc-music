package com.example.tcmusic.feature.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.tcmusic.core.designsystem.icon.TcMusicIcons
import com.example.tcmusic.core.designsystem.theme.Black
import com.example.tcmusic.core.designsystem.theme.BlueMystic
import com.example.tcmusic.core.designsystem.theme.BlueRibbon
import com.example.tcmusic.core.designsystem.theme.White
import com.example.tcmusic.core.model.Artist
import com.example.tcmusic.core.model.Track
import com.example.tcmusic.core.ui.ArtistCard
import com.example.tcmusic.core.ui.TrackCard
import kotlinx.coroutines.flow.flowOf

/**
 * Created by TC on 22/11/2022.
 */

@Composable
fun SearchRoute(
    onArtistClick: (String?) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val textSearch = viewModel.textSearch.collectAsState()
    val tracks = viewModel.tracks.collectAsLazyPagingItems()
    val artists = viewModel.artists.collectAsLazyPagingItems()

    SearchScreen(
        textSearch = textSearch.value,
        tracks = tracks,
        artists = artists,
        onTextSearchChanged = viewModel::search,
        onTrackClick = viewModel::clickTrack,
        onArtistClick = onArtistClick
    )
}

@Composable
fun SearchScreen(
    textSearch: String,
    tracks: LazyPagingItems<Track>,
    artists: LazyPagingItems<Artist>,
    onTextSearchChanged: (String) -> Unit,
    onTrackClick: (Track) -> Unit,
    onArtistClick: (String?) -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Header()

        SearchField(
            textSearch = textSearch,
            onTextSearchChanged = onTextSearchChanged
        )

        if (textSearch.length > 1) {
            Spacer(modifier = Modifier.height(10.dp))

            SearchCategoryTabs(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            when (selectedTab) {
                0 -> Tracks(
                    tracks = tracks,
                    onTrackClick = onTrackClick
                )
                1 -> Artists(
                    artists = artists,
                    onArtistClick = onArtistClick
                )
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Spacer(modifier = Modifier.width(20.dp))

        Image(
            painter = painterResource(id = TcMusicIcons.App),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.app_name),
            color = Black,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
        )
    }
}

@Composable
fun SearchField(
    textSearch: String,
    onTextSearchChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = textSearch,
        placeholder = {
            Text(
                text = stringResource(id = R.string.hint_search),
                maxLines = 1
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Black,
            disabledTextColor = Color.Transparent,
            backgroundColor = White,
            focusedBorderColor = BlueMystic,
            unfocusedBorderColor = BlueMystic
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, null)
        },
        onValueChange = onTextSearchChanged
    )
}

@Composable
fun SearchCategoryTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (selectedTab == 0) BlueRibbon else White,
                    RoundedCornerShape(20.dp)
                )
                .border(
                    1.dp,
                    if (selectedTab == 0) Color.Transparent else BlueMystic,
                    RoundedCornerShape(20.dp)
                )
                .clickable {
                    onTabSelected(0)
                }
        ) {
            Text(
                text = stringResource(id = R.string.text_songs),
                color = if (selectedTab == 0) White else Black,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .background(
                    if (selectedTab == 1) BlueRibbon else White,
                    RoundedCornerShape(20.dp)
                )
                .border(
                    1.dp,
                    if (selectedTab == 1) Color.Transparent else BlueMystic,
                    RoundedCornerShape(20.dp)
                )
                .clickable {
                    onTabSelected(1)
                }
        ) {
            Text(
                text = stringResource(id = R.string.text_artists),
                color = if (selectedTab == 1) White else Black,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun Tracks(
    tracks: LazyPagingItems<Track>,
    onTrackClick: (Track) -> Unit
) {
    // Do not use key because api search has error (duplicate item)
    LazyColumn {
        items(
            items = tracks
        ) { itemTrack ->
            itemTrack?.let {
                TrackCard(
                    track = it,
                    onClick = {
                        onTrackClick(it)
                    },
                    onMoreClick = {}
                )
            }
        }
    }
}

@Composable
fun Artists(
    artists: LazyPagingItems<Artist>,
    onArtistClick: (String?) -> Unit
) {
    LazyColumn {
        items(
            items = artists
        ) { itemArtist ->
            itemArtist?.let {
                ArtistCard(
                    artist = it,
                    onClick = {
                        onArtistClick(it.id)
                    },
                    onMoreClick =  { }
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        textSearch = "",
        tracks = flowOf(PagingData.empty<Track>()).collectAsLazyPagingItems(),
        artists = flowOf(PagingData.empty<Artist>()).collectAsLazyPagingItems(),
        onTextSearchChanged = { _ ->
        },
        onTrackClick = { _ ->
        },
        onArtistClick = { _ ->
        }
    )
}
