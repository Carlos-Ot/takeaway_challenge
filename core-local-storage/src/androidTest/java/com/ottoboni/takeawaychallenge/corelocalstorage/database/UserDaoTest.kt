package com.ottoboni.takeawaychallenge.corelocalstorage.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.UserEntity
import com.ottoboni.takeawaychallenge.corelocalstorage.userName
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest : DaoTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val userDao: UserDao = appDatabase.userDao()

    @Test
    fun insertAndGetUserTest() = runBlocking {
        val userEntity = UserEntity(id = 10, name = userName)

        val insertedId = userDao.insert(userEntity)
        val insertedUser = userDao.selectBy(insertedId)

        assertThat(insertedUser).isEqualTo(userEntity)
    }

    @Test
    fun insertAndDeleteUserTest() = runBlocking {
        val userEntity = UserEntity(id = 10, name = userName)

        userDao.insert(userEntity)
        userDao.deleteBy(userEntity.id)

        assertThat(userDao.selectBy(userEntity.id)).isNull()
    }
}
