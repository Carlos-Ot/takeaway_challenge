package com.ottoboni.corelocalstorage.di

import android.content.Context
import io.mockk.mockk
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class LocalStorageModuleTest : AutoCloseKoinTest() {

    @Test
    fun `test check modules for LocalStorageModule`() = koinApplication {
        modules(LocalStorageModule.instance + mockModules)
    }.checkModules()

    private val mockModules = module {
        factory<Context> { mockk(relaxed = true) }
    }
}