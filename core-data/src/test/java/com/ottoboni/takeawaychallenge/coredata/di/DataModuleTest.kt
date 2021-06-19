package com.ottoboni.takeawaychallenge.coredata.di

import com.ottoboni.takeawaychallenge.coredata.di.DataModule
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.RestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserRestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.JsonReader
import io.mockk.mockk
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class DataModuleTest : AutoCloseKoinTest() {

    @Test
    fun `test check modules for DataModule`() = koinApplication {
        modules(DataModule.instance + mockModules)
    }.checkModules()

    private val mockModules = module {
        factory<JsonReader> { mockk() }
        factory<RestaurantDao> { mockk(relaxed = true) }
        factory<UserRestaurantDao> { mockk(relaxed = true) }
    }
}