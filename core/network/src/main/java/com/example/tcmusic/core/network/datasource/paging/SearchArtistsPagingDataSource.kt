package com.example.tcmusic.core.network.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tcmusic.core.network.model.NetworkArtist
import com.example.tcmusic.core.network.retrofit.RetrofitShazamNetwork
import java.io.IOException
import retrofit2.HttpException

/**
 * Created by TC on 05/01/2023.
 */
class SearchArtistsPagingDataSource(
    private val retrofitShazamNetwork: RetrofitShazamNetwork,
    private val query: String?
) : PagingSource<Int, NetworkArtist>() {
    override fun getRefreshKey(state: PagingState<Int, NetworkArtist>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkArtist> {
        val offset = params.key ?: 0
        return try {
            val response = retrofitShazamNetwork.searchArtists(
                query = query,
                offset = offset
            ).artists?.hits?.mapNotNull { it.artist } ?: listOf()
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
