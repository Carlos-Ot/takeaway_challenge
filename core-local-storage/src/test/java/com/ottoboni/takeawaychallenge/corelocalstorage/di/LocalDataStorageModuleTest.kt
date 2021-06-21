package com.ottoboni.takeawaychallenge.corelocalstorage.di

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.ottoboni.takeawaychallenge.corelocalstorage.database.AppDatabase
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.RestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserRestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.JsonReader
import com.squareup.moshi.Moshi
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock

class LocalDataStorageModuleTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(LocalStorageModule.instance)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun `test should inject dependencies`() {
        declareMock<Context>()

        assertThat(get<AppDatabase>()).isNotNull()
        assertThat(get<RestaurantDao>()).isNotNull()
        assertThat(get<UserDao>()).isNotNull()
        assertThat(get<UserRestaurantDao>()).isNotNull()
        assertThat(get<Moshi>()).isNotNull()
        assertThat(get<JsonReader>()).isNotNull()
    }
}