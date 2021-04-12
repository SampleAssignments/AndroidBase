package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val city: String?,
    val country: String?,
    val id: Int,
    val lat: Double,
    val lng: Double,
    val printable_address: String?,
    val shortname: String?,
    val state: String?,
    val street: String?,
    val subpremise: String?,
    val zip_code: String?
) : Parcelable