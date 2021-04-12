package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(
    val id: Int,
    val is_business_enabled: Boolean?,
    val is_catering: Boolean,
    val menu_version: Int,
    val name: String,
    val open_hours: List<List<OpenHour>>,
    val popular_items: List<PopularItem>,
    val status: String,
    val status_type: String,
    val subtitle: String
) : Parcelable