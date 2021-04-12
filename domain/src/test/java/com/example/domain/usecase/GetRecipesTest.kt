package com.example.domain.usecase

import com.example.domain.RxImmediateSchedulerRule
import com.example.domain.model.RecipeResponse
import com.example.domain.repository.RecipeRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetRecipesTest {

    companion object {
        private val TEST_RECIPE = RecipeResponse(0, 0, emptyList(), 0)
    }

    @get:Rule
    var scheduler = RxImmediateSchedulerRule()

    @MockK
    lateinit var repository: RecipeRepository

    @InjectMockKs
    lateinit var subject: GetRecipes

    @Before
    fun setUp() = MockKAnnotations.init(this)

    private val testObserver = TestObserver.create<RecipeResponse>()

    @Test
    fun testGetRecipes() {
        every { repository.getRecipes() }.returns(Single.just(TEST_RECIPE))
        subject().subscribe(testObserver)
        with(testObserver) {
            assertComplete()
            assertValue(TEST_RECIPE)
        }
    }
}