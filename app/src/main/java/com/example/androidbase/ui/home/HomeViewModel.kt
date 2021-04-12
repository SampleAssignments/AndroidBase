package com.example.androidbase.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.paging.PagingData
import com.example.androidbase.BaseViewModel
import com.example.domain.model.*
import com.example.domain.usecase.restaurant.GetRestaurantFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRestaurantFeed: GetRestaurantFeed
) : BaseViewModel() {

    val restaurantFeedLiveData = LiveDataReactiveStreams.fromPublisher(storeFeedFlowable)

    private val storeFeedFlowable: Flowable<Result<PagingData<Restaurant>>>
        get() = getRestaurantFeed(StoreFeedQuery(Location(37.422740, -122.139956)))
            .map(Function<PagingData<Restaurant>, Result<PagingData<Restaurant>>> { Result.Success(it) })
            .onErrorReturn { Result.Error(it) }
            .startWith(Single.just(Result.Loading))
}