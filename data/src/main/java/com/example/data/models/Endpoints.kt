package com.example.data.models

import com.example.data.models.Endpoints.PATH.RESTAURANT_ID

object Endpoints {

    const val GET_RECIPES = "/recipes/complexSearch"
    const val GET_STORE_FEED = "/v1/store_feed"
    const val GET_RESTAURANT_DETAILS = "/v2/restaurant/{$RESTAURANT_ID}"


    object Headers {
        const val CONTENT_TYPE_JSON = "Content-Type:application/json"
    }

    object PATH {
        const val RESTAURANT_ID = "restaurant_id"
    }
}