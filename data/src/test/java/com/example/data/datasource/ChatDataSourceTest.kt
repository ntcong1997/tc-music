package com.example.data.datasource

import androidx.paging.PagingSource
import com.example.data.apiservice.FakeShazamApiService
import com.example.data.datasource.paging.WorldChartPagingDataSource
import com.example.domain.data.Track_1
import com.example.domain.data.Track_2
import com.example.domain.data.Track_3
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by TC on 03/11/2022.
 */
class ChatDataSourceTest {
    private val shazamApiService = FakeShazamApiService()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun get_world_chart_success() =
        runTest {
            val expectedResult = PagingSource.LoadResult.Page(
                data = listOf(
                    Track_1,
                    Track_2,
                    Track_3
                ),
                prevKey = null,
                nextKey = 1
            )

            val actualResult = WorldChartPagingDataSource(shazamApiService).load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 0,
                    placeholdersEnabled = false
                )
            )

            assertEquals(expectedResult, actualResult)
        }
}