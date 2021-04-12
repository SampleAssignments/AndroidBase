package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val address: Address? = null,
    val average_rating: Double,
    val business_id: Int,
    val cover_img_url: String?,
    val description: String?,
    val distance_from_consumer: Double = -1.0,
    val header_img_url: String?,
    val id: Int,
    val location: Location? = null,
    val menus: List<Menu>,
    val name: String?,
    val next_close_time: String? = null,
    val next_open_time: String? = null,
    val num_ratings: Int,
    val tags: List<String> = emptyList(),
    val url: String? = null
) : Parcelable
