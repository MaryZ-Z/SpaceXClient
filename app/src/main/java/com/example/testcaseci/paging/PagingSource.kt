package com.example.testcaseci.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testcaseci.data.launches.*
import com.example.testcaseci.data.repository.LaunchesRepository

class PagingSource(
    val repository: LaunchesRepository
) : PagingSource<Int, QueryLaunches>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, QueryLaunches> {
        return try {
            val nextPageNumber = params.key ?: 1
            val body =
                LaunchesBody(
                    query = DateUtc(FromToDate()),
                    options = Options("-date_utc", nextPageNumber)
                )
            val response = repository.getLaunches(body)
             LoadResult.Page(
                data = response.docs,
                prevKey = response.prevPage,
                nextKey = response.nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, QueryLaunches>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}