package com.example.coredata.di

import com.ottoboni.corelocalstorage.database.dao.RestaurantDao
import com.ottoboni.corelocalstorage.filestore.JsonReader
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
    }
}