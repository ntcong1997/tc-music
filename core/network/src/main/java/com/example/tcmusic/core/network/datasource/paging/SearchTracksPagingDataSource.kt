package com.example.tcmusic.core.network.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tcmusic.core.network.model.NetworkTrackV1
import com.example.tcmusic.core.network.retrofit.RetrofitShazamNetwork
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by TC on 05/01/2023.
 */
class SearchTracksPagingDataSource(
    private val retrofitShazamNetwork: RetrofitShazamNetwork,
    private val query: String?
) : PagingSource<Int, NetworkTrackV1>() {
    override fun getRefreshKey(state: PagingState<Int, NetworkTrackV1>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkTrackV1> {
        val offset = params.key ?: 0
        return try {
            val response = retrofitShazamNetwork.searchTracks(
                query = query,
                offset = offset
            ).tracks?.hits?.mapNotNull { it.track } ?: listOf<NetworkTrackV1>()
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
