package com.example.data.restaurant

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.domain.model.Restaurant
import com.example.domain.model.StoreFeed
import com.example.domain.model.StoreFeedQuery
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers

class RestaurantPagingSource(
    private val restaurantDataSource: RestaurantDataSource,
    private val query: StoreFeedQuery
) : RxPagingSource<Int, Restaurant>() {
    override fun getRefreshKey(state: PagingState<Int, Restaurant>): Int? {
        val anchorPosition: Int = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null

        val prevKey = anchorPage.prevKey
        if (prevKey != null) return prevKey + query.limit

        val nextKey = anchorPage.nextKey
        return if (nextKey != null) nextKey - query.limit else null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Restaurant>> {
        val next = params.key ?: 0
        return restaurantDataSource.getRestaurantFeed(query.copy(offset = next))
            .subscribeOn(Schedulers.io())
            .map(Function<StoreFeed, LoadResult<Int, Restaurant>> { it.toLoadResult() })
            .onErrorReturn(Function<Throwable, LoadResult<Int, Restaurant>> { LoadResult.Error(it) })
    }

    private fun StoreFeed.toLoadResult(): LoadResult.Page<Int, Restaurant> {
        return LoadResult.Page(
            data = stores,
            prevKey = null,
            nextKey = next_offset
        )
    }
}