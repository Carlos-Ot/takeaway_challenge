package com.ottoboni.takeawaychallenge.coredata.di

import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.RestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserRestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.JsonReader
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock

class DataModuleTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun `test check modules for DataModule`() = checkModules {
        declareMock<JsonReader>()
        declareMock<RestaurantDao>()
        declareMock<UserRestaurantDao>()
        modules(DataModule.instance)
    }
}
