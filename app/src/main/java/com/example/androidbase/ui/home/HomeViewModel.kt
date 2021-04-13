package com.example.androidbase.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.paging.PagingData
import com.example.androidbase.BaseViewModel
import com.example.domain.model.Location
import com.example.domain.model.Restaurant
import com.example.domain.model.Result
import com.example.domain.model.StoreFeedQuery
import com.example.domain.usecase.restaurant.GetRestaurantFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRestaurantFeed: GetRestaurantFeed
) : BaseViewModel() {

    fun getRestaurantFeed(location: Location): LiveData<Result<PagingData<Restaurant>>> {
        val storeFeedFlowable = getRestaurantFeed(StoreFeedQuery(location))
            .map(Function<PagingData<Restaurant>, Result<PagingData<Restaurant>>> {
                Result.Success(it)
            })
            .onErrorReturn { Result.Error(it) }
            .startWith(Single.just(Result.Loading))

        return LiveDataReactiveStreams.fromPublisher(storeFeedFlowable)
    }
}