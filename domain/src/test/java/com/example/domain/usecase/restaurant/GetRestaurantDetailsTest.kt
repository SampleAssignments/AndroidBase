package com.example.domain.usecase.restaurant

import com.example.domain.repository.RestaurantRepository
import com.example.domain.usecase.BaseTest
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test


class GetRestaurantDetailsTest : BaseTest() {

    @MockK
    lateinit var repository: RestaurantRepository

    @InjectMockKs
    lateinit var subject: GetRestaurantDetails

    @Test
    fun `test getRestaurantDetails with invalid id`() {
        subject("").subscribe(testObserver)

        with(testObserver) {
            assertNotComplete()
            assertError {
                it.localizedMessage == "id is incorrect"
            }
        }
    }

    @Test
    fun `test getRestaurantDetails with valid id`() {
        every { repository.getRestaurantDetails(any()) } returns Single.just(mockk(relaxed = true))

        subject("1243").subscribe(testObserver)

        with(testObserver) {
            assertComplete()
            assertNoErrors()
        }
    }
}