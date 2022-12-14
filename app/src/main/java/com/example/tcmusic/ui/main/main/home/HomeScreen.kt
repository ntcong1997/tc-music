package com.example.tcmusic.ui.main.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tcmusic.R
import com.example.tcmusic.ui.main.main.home.dancechart.DanceChartScreen
import com.example.tcmusic.ui.main.main.home.electronicchart.ElectronicChartScreen
import com.example.tcmusic.ui.main.main.home.hiphoprapchart.HipHopRapChartScreen
import com.example.tcmusic.ui.main.main.home.popchart.PopChartScreen
import com.example.tcmusic.ui.main.main.home.rockchart.RockChartScreen
import com.example.tcmusic.ui.main.main.home.worldchart.WorldChartScreen
import com.example.tcmusic.ui.theme.Black
import com.example.tcmusic.ui.theme.BlueRibbon
import com.example.tcmusic.ui.theme.GrayMercury
import com.example.tcmusic.ui.theme.White
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

/**
 * Created by TC on 10/10/2022.
 */

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen()
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun HomeScreen() {
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
            text = stringResource(id = R.string.home_screen_text_app_name),
            color = Black,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState()

    val listTab = listOf(
        R.string.home_screen_text_world_chart,
        R.string.home_screen_text_pop_chart,
        R.string.home_screen_text_hip_hop_rap_chart,
        R.string.home_screen_text_dance_chart,
        R.string.home_screen_text_electronic_chart,
        R.string.home_screen_text_rock_chart
    )

    Tabs(listTab = listTab, pagerState = pagerState)
    TabsContent(
        listTabSize = listTab.size,
        pagerState = pagerState
    )
}

@ExperimentalPagerApi
@Composable
fun Tabs(
    listTab: List<Int>,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()

    ScrollableTabRow(
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

@ExperimentalMaterialApi
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
            2 -> HipHopRapChartScreen()
            3 -> DanceChartScreen()
            4 -> ElectronicChartScreen()
            5 -> RockChartScreen()
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
