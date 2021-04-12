package com.example.domain.repository

import com.example.domain.model.Restaurant
import com.example.domain.model.StoreFeed
import com.example.domain.model.StoreFeedQuery
import io.reactivex.rxjava3.core.Single

interface RestaurantRepository : Repository {

    fun getRestaurantDetails(id: String): Single<Restaurant>

    fun getRestaurantFeed(storeFeedQuery: StoreFeedQuery): Single<StoreFeed>
}