package com.example.tcmusic.ui.main.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
    mainNavController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        mainNavController = mainNavController
    )
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(
    mainNavController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Header()

        TabLayout(mainNavController)
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

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun TabLayout(
    mainNavController: NavController
) {
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
        mainNavController = mainNavController,
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
    mainNavController: NavController,
    listTabSize: Int,
    pagerState: PagerState
) {
    HorizontalPager(
        state = pagerState,
        count = listTabSize
    ) { page ->
        when (page) {
            0 -> WorldChartScreen(mainNavController)
            1 -> PopChartScreen(mainNavController)
            2 -> HipHopRapChartScreen(mainNavController)
            3 -> DanceChartScreen(mainNavController)
            4 -> ElectronicChartScreen(mainNavController)
            5 -> RockChartScreen(mainNavController)
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()

    HomeScreen(navController)
}
