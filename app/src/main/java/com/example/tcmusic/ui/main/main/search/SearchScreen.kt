package com.example.tcmusic.ui.main.main.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.model.Track
import com.example.tcmusic.R
import com.example.tcmusic.ui.main.main.home.TrackItem
import com.example.tcmusic.ui.theme.Black
import com.example.tcmusic.ui.theme.BlueMystic
import com.example.tcmusic.ui.theme.White
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.util.*

/**
 * Created by TC on 22/11/2022.
 */

@FlowPreview
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val textSearch = viewModel.textSearch.collectAsState()
    val tracks = viewModel.tracks.collectAsLazyPagingItems()

    SearchScreen(
        textSearch = textSearch.value,
        tracks = tracks,
        onTextSearchChanged = viewModel::search
    )
}

@Composable
fun SearchScreen(
    textSearch: String,
    tracks: LazyPagingItems<Track>,
    onTextSearchChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Header()

        SearchField(
            textSearch = textSearch,
            onTextSearchChanged = {
                onTextSearchChanged(it)
            }
        )

        if (textSearch.length > 1) {
            Tracks(tracks = tracks)
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
            painter = painterResource(id = R.drawable.ic_app),
            contentDescription = null
        )

        Text(
            text = stringResource(id = R.string.search_screen_text_app_name),
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
            Text(text = stringResource(id = R.string.search_screen_hint_search))
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
        onValueChange = {
            onTextSearchChanged(it)
        }
    )
}

@Composable
fun Tracks(
    tracks: LazyPagingItems<Track>
) {
    LazyColumn {
        items(
            items = tracks,
            key = {
                it.key ?: UUID.randomUUID().toString()
            }
        ) { itemTrack ->
            itemTrack?.let {
                TrackItem(
                    track = it,
                    onClickTrack = {

                    }
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
        onTextSearchChanged = { _ ->

        }
    )
}