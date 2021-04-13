package com.example.data.utils

import com.example.domain.model.Location
import com.example.domain.model.StoreFeedQuery
import org.junit.Test

class ExtensionKtTest {

    private val storeFeedQuery = StoreFeedQuery(Location())

    @Test
    fun `test storeFeedQueryMap`() {
        val map = storeFeedQuery.toQueryMap()
        assert(map["lat"] == "0.0")
        assert(map["lng"] == "0.0")
        assert(map["offset"] == "0")
        assert(map["limit"] == "30")
    }
}