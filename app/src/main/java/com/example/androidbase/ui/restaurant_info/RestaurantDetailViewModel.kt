package com.example.androidbase.ui.restaurant_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.androidbase.BaseViewModel
import com.example.domain.model.Restaurant
import com.example.domain.model.Result
import com.example.domain.usecase.restaurant.GetRestaurantDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    private val getRestaurantDetails: GetRestaurantDetails
) : BaseViewModel() {

    fun getRestaurantInfo(id: String): LiveData<Result<Restaurant>> {
        val restaurantDetailsFlowable = getRestaurantDetails(id)
            .map(Function<Restaurant, Result<Restaurant>> {
                Result.Success(it)
            })
            .onErrorReturn { Result.Error(it) }
            .toFlowable()
            .startWith(Single.just(Result.Loading))

        return LiveDataReactiveStreams.fromPublisher(restaurantDetailsFlowable)
    }
}