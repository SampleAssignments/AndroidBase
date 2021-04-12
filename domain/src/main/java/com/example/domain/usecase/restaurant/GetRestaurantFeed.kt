package com.example.domain.usecase.restaurant

import androidx.paging.PagingData
import com.example.domain.model.Restaurant
import com.example.domain.model.StoreFeedQuery
import com.example.domain.repository.RestaurantRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetRestaurantFeed @Inject constructor(
    restaurantRepository: RestaurantRepository
) : RestaurantRepository by restaurantRepository {

    operator fun invoke(query: StoreFeedQuery): Flowable<PagingData<Restaurant>> {
        return with(query) {
            if (location.lat == 0.0 || location.lng == 0.0) {
                Flowable.error(Throwable("one of the required params is missing"))
            } else {
                getRestaurantFeed(query)
            }
        }
    }
}