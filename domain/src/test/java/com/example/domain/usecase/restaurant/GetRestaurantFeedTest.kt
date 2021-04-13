package com.example.domain.usecase.restaurant

import com.example.domain.model.Location
import com.example.domain.model.StoreFeedQuery
import com.example.domain.repository.RestaurantRepository
import com.example.domain.usecase.BaseTest
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Test

class GetRestaurantFeedTest : BaseTest() {

    @MockK
    lateinit var repository: RestaurantRepository

    @InjectMockKs
    lateinit var subject: GetRestaurantFeed

    @Test
    fun `test getRestaurantFeed with invalid lat lng`() {
        subject(StoreFeedQuery(Location())).subscribe(testSubscriber)

        with(testSubscriber) {
            assertNotComplete()
            assertError {
                it.localizedMessage == "one of the required params is missing"
            }
        }
    }

    @Test
    fun `test getRestaurantFeed with valid lat lng`() {
        every { repository.getRestaurantFeed(any()) } returns mockk(relaxed = true)
        subject(StoreFeedQuery(Location(37.422740, -122.139956))).subscribe(testSubscriber)

        with(testSubscriber) {
            assertNoErrors()
        }
    }
}