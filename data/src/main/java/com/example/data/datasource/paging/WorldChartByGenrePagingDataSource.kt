package com.example.data.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.apiservice.ShazamApiService
import com.example.model.GenreCode
import com.example.model.Track
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by TC on 19/10/2022.
 */

class WorldChartByGenrePagingDataSource(
    private val genreCode: GenreCode,
    private val shazamApiService: ShazamApiService
) : PagingSource<Int, Track>() {
    override fun getRefreshKey(state: PagingState<Int, Track>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        val offset = params.key ?: 0
        return try {
            val response = shazamApiService.getWorldChartByGenre(genreCode.value, offset)
            LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = if (response.isEmpty()) null else offset + 1
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