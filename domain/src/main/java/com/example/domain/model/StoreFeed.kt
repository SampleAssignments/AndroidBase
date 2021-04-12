package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreFeed(
    val next_offset: Int,
    val num_results: Int,
    val stores: List<Restaurant>
) : Parcelable
