package com.ottoboni.takeawaychallenge.coredata.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MainDispatcherMap : DispatcherMap {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val ui: CoroutineDispatcher
        get() = Dispatchers.Main
    override val computation: CoroutineDispatcher
        get() = Dispatchers.Default
}