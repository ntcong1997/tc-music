package com.example.data.datasource

import com.example.data.apiservice.FakeShazamApiService
import com.example.data.datasource.paging.WorldChartPagingDataSource
import org.junit.Test

/**
 * Created by TC on 03/11/2022.
 */
class ChatDataSourceTest {
    private val shazamApiService = FakeShazamApiService()
    private val chartDataSource: ChartDataSource = ChartDataSourceImpl(shazamApiService)

    @Test
    fun getWorldChart() {
        val worldChart = chartDataSource.loadWorldChart()
    }
}