package com.example.androidbase.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import com.example.androidbase.BaseViewModel
import com.example.domain.model.Location
import com.example.domain.model.Result
import com.example.domain.model.StoreFeed
import com.example.domain.model.StoreFeedQuery
import com.example.domain.usecase.GetRestaurantFeed
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

    private val storeFeedFlowable: Flowable<Result<StoreFeed>>
        get() = getRestaurantFeed(StoreFeedQuery(Location(37.422740, -122.139956)))
            .map(Function<StoreFeed, Result<StoreFeed>> {
                Result.Success(it)
            })
            .onErrorReturn {
                Result.Error(it)
            }
            .toFlowable()
            .startWith(Single.just(Result.Loading))
}