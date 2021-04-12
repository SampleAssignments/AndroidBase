package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Restaurant
import com.example.domain.model.StoreFeedQuery
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface RestaurantRepository : Repository {

    fun getRestaurantDetails(id: String): Single<Restaurant>

    fun getRestaurantFeed(storeFeedQuery: StoreFeedQuery): Flowable<PagingData<Restaurant>>
}