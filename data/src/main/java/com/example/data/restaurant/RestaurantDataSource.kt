package com.example.data.restaurant

import com.example.data.api.RestaurantService
import com.example.data.utils.toQueryMap
import com.example.domain.model.Restaurant
import com.example.domain.model.StoreFeed
import com.example.domain.model.StoreFeedQuery
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface RestaurantDataSource {

    fun getRestaurantFeed(query: StoreFeedQuery): Single<StoreFeed>

    fun getRestaurantDetails(id: String): Single<Restaurant>
}

class RestaurantRemoteDataSource @Inject constructor(
    private val service: RestaurantService
) : RestaurantDataSource {

    override fun getRestaurantFeed(query: StoreFeedQuery): Single<StoreFeed> {
        return service.getStoreFeed(query.toQueryMap()).map { it.toStoreFeed() }
    }

    override fun getRestaurantDetails(id: String): Single<Restaurant> {
        return service.getRestaurantDetails(id).map { it.toRestaurant() }
    }
}