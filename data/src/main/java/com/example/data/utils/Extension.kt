package com.example.data.utils

import com.example.domain.model.StoreFeedQuery

fun StoreFeedQuery.toQueryMap(): Map<String, String> {
    return mutableMapOf<String, String>().apply {
        put("lat", "${location.lat}")
        put("lng", "${location.lng}")
        put("offset", "$offset")
        put("limit", "$limit")
    }
}