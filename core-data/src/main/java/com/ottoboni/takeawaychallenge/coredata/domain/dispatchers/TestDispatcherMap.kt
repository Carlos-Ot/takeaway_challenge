package com.ottoboni.takeawaychallenge.coredata.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatcherMap : DispatcherMap {
    override val io: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val ui: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val computation: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}