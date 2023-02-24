package com.example.tcmusic.core.network.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tcmusic.core.network.model.NetworkTrackV1
import com.example.tcmusic.core.network.retrofit.RetrofitShazamNetwork
import java.io.IOException
import retrofit2.HttpException

/**
 * Created by TC on 19/10/2022.
 */

class WorldChartByGenrePagingDataSource(
    private val genreCode: String?,
    private val retrofitShazamNetwork: RetrofitShazamNetwork
) : PagingSource<Int, NetworkTrackV1>() {
    override fun getRefreshKey(state: PagingState<Int, NetworkTrackV1>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkTrackV1> {
        val offset = params.key ?: 0
        return try {
            val response = retrofitShazamNetwork.getWorldChartByGenre(genreCode, offset)
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
