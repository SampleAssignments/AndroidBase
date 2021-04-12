package com.example.data.restaurant

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.data.di.ApiDataSource
import com.example.domain.model.Restaurant
import com.example.domain.model.StoreFeedQuery
import com.example.domain.repository.RestaurantRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    @ApiDataSource private val remoteDataSource: RestaurantDataSource
) : RestaurantRepository {

    override fun getRestaurantDetails(id: String): Single<Restaurant> {
        return remoteDataSource.getRestaurantDetails(id)
    }

    @ExperimentalCoroutinesApi
    override fun getRestaurantFeed(storeFeedQuery: StoreFeedQuery): Flowable<PagingData<Restaurant>> {
        return Pager(
            PagingConfig(
                pageSize = storeFeedQuery.limit,
                enablePlaceholders = false
            )
        ) {
            RestaurantPagingSource(remoteDataSource, storeFeedQuery)
        }.flowable
    }
}