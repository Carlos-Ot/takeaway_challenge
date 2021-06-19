package com.ottoboni.takeawaychallenge.coredomain.di

import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantExternalDataSource
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantLocalDataSource
import io.mockk.mockk
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class DomainModuleTest : AutoCloseKoinTest() {

    @Test
    fun `test check modules for DomainModule`() = koinApplication {
        modules(DomainModule.instance + mockModules)
    }.checkModules()

    private val mockModules = module {
        factory<RestaurantLocalDataSource> { mockk() }
        factory<RestaurantExternalDataSource> { mockk() }
    }
}