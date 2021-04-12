package com.example.data.models

import com.example.domain.model.StoreFeed

data class StoreFeedResponse(
    val is_first_time_user: Boolean,
    val next_offset: Int,
    val num_results: Int,
    val show_list_as_pickup: Boolean,
    val sort_order: String,
    val stores: List<StoreResponse>
) {
    fun toStoreFeed(): StoreFeed {
        return StoreFeed(
            next_offset = next_offset,
            num_results = num_results,
            stores = stores.map { it.toRestaurant() }
        )
    }
}