package com.example.domain.usecase.restaurant

import com.example.domain.model.Restaurant
import com.example.domain.repository.RestaurantRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRestaurantDetails @Inject constructor(
    restaurantRepository: RestaurantRepository
) : RestaurantRepository by restaurantRepository {

    operator fun invoke(id: String): Single<Restaurant> {
        return if (id.isNotEmpty()) {
            getRestaurantDetails(id)
        } else {
            Single.error(Throwable("id is incorrect"))
        }
    }
}