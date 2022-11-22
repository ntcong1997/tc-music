package com.example.tcmusic.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tcmusic.R
import com.example.tcmusic.ui.main.home.popchart.PopChartScreen
import com.example.tcmusic.ui.main.home.worldchart.WorldChartScreen
import com.example.tcmusic.ui.theme.Black
import com.example.tcmusic.ui.theme.BlueRibbon
import com.example.tcmusic.ui.theme.GrayMercury
import com.example.tcmusic.ui.theme.White
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by TC on 10/10/2022.
 */


@ExperimentalPagerApi
@Composable
fun HomeScreen(
    navController: NavController
) {
    HomeScreen()
}

@ExperimentalPagerApi
@Composable
fun HomeScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Header()

        TabLayout()
    }
}

@ExperimentalPagerApi
@Composable
fun Header() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(20.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_app),
            contentDescription = null,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        Text(
            text = context.getString(R.string.home_screen_text_app_name),
            color = Black,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(1f)
                .padding(20.dp)
        )
    }
}

@ExperimentalPagerApi
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState()

    val listTab = listOf(
        R.string.home_screen_text_world_chart,
        R.string.home_screen_text_pop_chart
    )

    Tabs(listTab = listTab, pagerState = pagerState)
    TabsContent(listTabSize = listTab.size, pagerState = pagerState)
}

@ExperimentalPagerApi
@Composable
fun Tabs(
    listTab: List<Int>,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = White,
        contentColor = BlueRibbon,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = BlueRibbon
            )
        }
    ) {
        listTab.forEachIndexed { index, _ ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
                text = {
                    val color = if (pagerState.currentPage == index) BlueRibbon else GrayMercury
                    Text(
                        text = stringResource(listTab[index]),
                        color = color,
                        fontSize = 13.sp
                    )
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    listTabSize: Int,
    pagerState: PagerState
) {
    HorizontalPager(
        state = pagerState,
        count = listTabSize
    ) { page ->
        when (page) {
            0 -> WorldChartScreen()
            1 -> PopChartScreen()
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}