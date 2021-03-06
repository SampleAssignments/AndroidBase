package com.example.data.models

import com.example.domain.model.Location
import com.example.domain.model.Menu
import com.example.domain.model.Restaurant

data class StoreResponse(
    val average_rating: Double,
    val business_id: Int,
    val cover_img_url: String,
    val delivery_fee: Int,
    val delivery_fee_monetary_fields: DeliveryFeeMonetaryFields,
    val description: String,
    val display_delivery_fee: String,
    val distance_from_consumer: Double,
    val extra_sos_delivery_fee: Int,
    val extra_sos_delivery_fee_monetary_fields: ExtraSosDeliveryFeeMonetaryFields,
    val header_img_url: String,
    val id: Int,
    val is_consumer_subscription_eligible: Boolean,
    val is_newly_added: Boolean,
    val location: Location,
    val menus: List<Menu>,
    val merchant_promotions: List<MerchantPromotion>,
    val name: String,
    val next_close_time: String,
    val next_open_time: String,
    val num_ratings: Int,
    val price_range: Int,
    val promotion_delivery_fee: Int,
    val service_rate: Long?,
    val status: Status,
    val url: String,
) {
    fun toRestaurant(): Restaurant {
        return Restaurant(
            average_rating = average_rating,
            business_id = business_id,
            cover_img_url = cover_img_url,
            description = description,
            distance_from_consumer = distance_from_consumer,
            header_img_url = header_img_url,
            id = id,
            location = location,
            menus = menus,
            name = name,
            next_close_time = next_close_time,
            next_open_time = next_open_time,
            num_ratings = num_ratings,
            url = url
        )
    }
}