package com.example.tcmusic.core.network.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tcmusic.core.network.model.NetworkTrack
import com.example.tcmusic.core.network.retrofit.RetrofitShazamNetwork
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by TC on 19/10/2022.
 */

class WorldChartPagingDataSource(
    private val retrofitShazamNetwork: RetrofitShazamNetwork
) : PagingSource<Int, NetworkTrack>() {
    override fun getRefreshKey(state: PagingState<Int, NetworkTrack>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkTrack> {
        val offset = params.key ?: 0
        return try {
            val response = retrofitShazamNetwork.getWorldChart(offset)
            LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = if (response.isEmpty()) null else offset + response.size
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}