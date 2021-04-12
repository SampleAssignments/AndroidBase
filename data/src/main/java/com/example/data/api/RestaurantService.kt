package com.example.data.api

import com.example.data.models.Endpoints.GET_RESTAURANT_DETAILS
import com.example.data.models.Endpoints.GET_STORE_FEED
import com.example.data.models.Endpoints.Headers.CONTENT_TYPE_JSON
import com.example.data.models.Endpoints.PATH.RESTAURANT_ID
import com.example.data.models.RestaurantResponse
import com.example.data.models.StoreFeedResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

import retrofit2.http.QueryMap

interface RestaurantService {

    @GET(GET_STORE_FEED)
    @Headers(CONTENT_TYPE_JSON)
    fun getStoreFeed(
        @QueryMap queryMap: Map<String, String>
    ): Single<StoreFeedResponse>

    @GET(GET_RESTAURANT_DETAILS)
    @Headers(CONTENT_TYPE_JSON)
    fun getRestaurantDetails(
        @Path(RESTAURANT_ID) restaurantId: String
    ): Single<RestaurantResponse>
}