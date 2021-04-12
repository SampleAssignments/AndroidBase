package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreFeedQuery(
    val location: Location,
    val offset: Int = 0,
    val limit: Int = DEFAULT_LIMIT_SIZE
) : Parcelable {
    companion object {
        private const val DEFAULT_LIMIT_SIZE = 30
    }
}
