package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val address: Address?,
    val average_rating: Double,
    val business_id: Int,
    val cover_img_url: String?,
    val description: String?,
    val distance_from_consumer: Double,
    val header_img_url: String?,
    val id: Int,
    val location: Location?,
    val menus: List<Menu>,
    val name: String?,
    val next_close_time: String?,
    val next_open_time: String?,
    val num_ratings: Int,
    val tags: List<String>,
    val url: String?
) : Parcelable
