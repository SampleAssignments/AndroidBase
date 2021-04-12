package com.example.data

import android.os.Parcelable
import java.util.concurrent.TimeUnit

interface CacheSource {

    fun <T : Parcelable> retrieveFromCache(key: String): T?

    fun <T : Parcelable> storeInCache(
        key: String,
        anyObject: T,
        ttl: Long = -1,
        timeUnit: TimeUnit = TimeUnit.MINUTES
    )

    fun evictFromCache(key: String)

    fun evictAll()
}