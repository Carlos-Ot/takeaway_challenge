package com.ottoboni.takeawaychallenge.coredomain.di

import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantExternalDataSource
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantLocalDataSource
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock

class DomainModuleTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun `test check modules for DomainModule`() = checkModules {
        declareMock<RestaurantLocalDataSource>()
        declareMock<RestaurantExternalDataSource>()
        modules(DomainModule.instance)
    }
}
