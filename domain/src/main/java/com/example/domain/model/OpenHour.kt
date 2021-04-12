package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OpenHour(
    val hour: Int,
    val minute: Int
): Parcelable