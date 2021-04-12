package com.example.data.restaurant

import com.example.data.di.ApiDataSource
import com.example.domain.model.Restaurant
import com.example.domain.model.StoreFeed
import com.example.domain.model.StoreFeedQuery
import com.example.domain.repository.RestaurantRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
  @ApiDataSource private val remoteDataSource: RestaurantDataSource
) : RestaurantRepository {

    override fun getRestaurantDetails(id: String): Single<Restaurant> {
        return remoteDataSource.getRestaurantDetails(id)
    }

    override fun getRestaurantFeed(storeFeedQuery: StoreFeedQuery): Single<StoreFeed> {
        return remoteDataSource.getRestaurantFeed(storeFeedQuery)
    }
}