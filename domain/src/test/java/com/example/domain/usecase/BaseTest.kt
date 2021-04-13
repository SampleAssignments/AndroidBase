package com.example.domain.usecase

import com.example.domain.RxImmediateSchedulerRule
import com.example.domain.model.Restaurant
import io.mockk.MockKAnnotations
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseTest {

    @get:Rule
    val immediateSchedulerRule = RxImmediateSchedulerRule()

    protected lateinit var testObserver: TestObserver<Any>
    protected lateinit var testSubscriber: TestSubscriber<Any>

    @Before
    open fun setUp() {
        testObserver = TestObserver()
        testSubscriber = TestSubscriber.create()
        MockKAnnotations.init(this)
    }

    @After
    open fun tearDown() {
        testObserver.dispose()
        testSubscriber.cancel()
    }
}