package com.ottoboni.takeawaychallenge.corelocalstorage.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.RestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserRestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.RestaurantEntity
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.UserEntity
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.UserRestaurantEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserRestaurantDaoTest : DaoTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val userDao: UserDao = appDatabase.userDao()
    private val restaurantDao: RestaurantDao = appDatabase.restaurantDao()
    private val userRestaurantDao: UserRestaurantDao = appDatabase.userRestaurantDao()

    private val userEntity = UserEntity(id = 10, name = "Test User")
    private val restaurantEntity =
        RestaurantEntity(id = 10, name = "Test Restaurant", status = "open", sortingValues = null)
    private val userRestaurantEntity = UserRestaurantEntity(userEntity.id, restaurantEntity.id)

    @Before
    fun setup() = runBlocking<Unit> {
        userDao.insert(userEntity)
        restaurantDao.insert(restaurantEntity)
    }

    @Test
    fun insertAndSelectUserRestaurant() = runBlocking {
        userRestaurantDao.insert(userRestaurantEntity)

        val userRestaurant = userRestaurantDao.selectBy(userEntity.id, restaurantEntity.id)

        assertThat(userRestaurant).isEqualTo(userRestaurantEntity)
    }

    @Test
    fun insertAndDeleteUserRestaurant() = runBlocking {
        userRestaurantDao.insert(userRestaurantEntity)

        userRestaurantDao.delete(userRestaurantEntity)

        assertThat(userRestaurantDao.selectBy(userEntity.id, restaurantEntity.id)).isNull()
    }

    @Test
    fun userRestaurantCascadeWhenUserIsDeleted() = runBlocking {
        userRestaurantDao.insert(userRestaurantEntity)

        userDao.deleteBy(userEntity.id)

        assertThat(userRestaurantDao.selectBy(userEntity.id, restaurantEntity.id)).isNull()
    }
}
