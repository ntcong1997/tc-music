package com.example.tcmusic.feature.home

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
import com.example.tcmusic.core.designsystem.icon.TcMusicIcons
import com.example.tcmusic.core.designsystem.theme.Black
import com.example.tcmusic.core.designsystem.theme.BlueRibbon
import com.example.tcmusic.core.designsystem.theme.GrayMercury
import com.example.tcmusic.core.designsystem.theme.White
import com.example.tcmusic.feature.home.dancechart.DanceChartScreen
import com.example.tcmusic.feature.home.electronicchart.ElectronicChartScreen
import com.example.tcmusic.feature.home.hiphoprapchart.HipHopRapChartScreen
import com.example.tcmusic.feature.home.popchart.PopChartScreen
import com.example.tcmusic.feature.home.rockchart.RockChartScreen
import com.example.tcmusic.feature.home.worldchart.WorldChartScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

/**
 * Created by TC on 20/02/2023.
 */

@Composable
fun HomeRoute(
    
) {
    HomeScreen()
}

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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState()

    val listTab = listOf(
        R.string.text_world_chart,
        R.string.text_pop_chart,
        R.string.text_hip_hop_rap_chart,
        R.string.text_dance_chart,
        R.string.text_electronic_chart,
        R.string.text_rock_chart
    )

    Tabs(listTab = listTab, pagerState = pagerState)
    TabsContent(
        listTabSize = listTab.size,
        pagerState = pagerState
    )
}

@OptIn(ExperimentalPagerApi::class)
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

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
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

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}