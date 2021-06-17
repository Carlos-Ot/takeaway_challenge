package com.example.coredata.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherMap {

    val io: CoroutineDispatcher
    val ui: CoroutineDispatcher
    val computation: CoroutineDispatcher
}