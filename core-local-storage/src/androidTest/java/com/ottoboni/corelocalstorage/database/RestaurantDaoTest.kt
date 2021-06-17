package com.ottoboni.corelocalstorage.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ottoboni.corelocalstorage.database.entity.RestaurantEntity
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestaurantDaoTest : DaoTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val restaurantDao = appDatabase.restaurantDao()

    @Test
    fun insertAndGetRestaurant() = runBlocking {
        val restaurantEntity = RestaurantEntity(id = 10, name = "Lamen San")

        val insertedId = restaurantDao.insert(restaurantEntity)
        val insertedRestaurant = restaurantDao.selectBy(insertedId)

        assertThat(insertedRestaurant).isEqualTo(restaurantEntity)
    }

    @Test
    fun insertAndDeleteRestaurantTest() = runBlocking {
        val restaurantEntity = RestaurantEntity(id = 10, name = "Lamen San")

        restaurantDao.insert(restaurantEntity)
        restaurantDao.deleteSafelyBy(restaurantEntity.id)

        assertThat(restaurantDao.selectBy(restaurantEntity.id)).isNull()
    }
}