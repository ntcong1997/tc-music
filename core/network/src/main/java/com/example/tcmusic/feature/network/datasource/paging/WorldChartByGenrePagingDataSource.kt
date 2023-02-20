package com.example.tcmusic.feature.network.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tcmusic.feature.network.model.NetworkTrack
import com.example.tcmusic.feature.network.retrofit.RetrofitShazamNetwork
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by TC on 19/10/2022.
 */

class WorldChartByGenrePagingDataSource(
    private val genreCode: String?,
    private val retrofitShazamNetwork: RetrofitShazamNetwork
) : PagingSource<Int, NetworkTrack>() {
    override fun getRefreshKey(state: PagingState<Int, NetworkTrack>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkTrack> {
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
