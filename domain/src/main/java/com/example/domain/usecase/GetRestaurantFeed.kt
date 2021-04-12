package com.example.domain.usecase

import com.example.domain.model.StoreFeed
import com.example.domain.model.StoreFeedQuery
import com.example.domain.repository.RestaurantRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRestaurantFeed @Inject constructor(
    restaurantRepository: RestaurantRepository
) : RestaurantRepository by restaurantRepository {

    operator fun invoke(query: StoreFeedQuery): Single<StoreFeed> {
        return with(query) {
            if (location.lat == 0.0 || location.lng == 0.0) {
                Single.error(Throwable("one of the required params is missing"))
            } else {
                getRestaurantFeed(query)
            }
        }
    }
}