package com.example.data

import android.os.Parcelable
import android.util.LruCache
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CacheSourceImpl @Inject constructor() : CacheSource {

    companion object {
        private const val MAX_CACHE_SIZE = 10 * 1024 * 1024
        private val lruCache = LruCache<String, Pair<Long, Parcelable>>(MAX_CACHE_SIZE)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Parcelable> retrieveFromCache(key: String): T? {
        synchronized(lruCache) {
            lruCache.get(key)?.run {
                if (first == Long.MAX_VALUE || first > System.currentTimeMillis()) {
                    return try {
                        second as T?
                    } catch (exe: ClassCastException) {
                        null
                    }
                } else {
                    evictFromCache(key) // as it has expired, evicting from cache
                }
            }
        }

        return null
    }

    override fun <T : Parcelable> storeInCache(
        key: String,
        anyObject: T,
        ttl: Long,
        timeUnit: TimeUnit
    ) {
        synchronized(lruCache) {
            val ttlInMillis = if (ttl < 0) {
                Long.MAX_VALUE
            } else {
                System.currentTimeMillis() + timeUnit.toMillis(ttl)
            }

            lruCache.put(key, ttlInMillis to anyObject)
        }
    }

    override fun evictFromCache(key: String) {
        synchronized(lruCache) {
            lruCache.snapshot().keys
                .filter { it.contains(key) }
                .forEach { lruCache.remove(it) }
        }
    }

    override fun evictAll() {
        synchronized(lruCache) {
            lruCache.evictAll()
        }
    }
}