package com.ottoboni.corelocalstorage.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ottoboni.corelocalstorage.database.dao.UserDao
import com.ottoboni.corelocalstorage.database.entity.UserEntity
import com.ottoboni.corelocalstorage.userName
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var userDao: UserDao
    private lateinit var testDatabase: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        testDatabase = AppDatabase.buildDatabase(context, true)
        userDao = testDatabase.userDao()
    }

    @Test
    fun insertAndGetUserTest() = runBlocking {
        val userEntity = UserEntity(id = 10, name = userName)

        val insertedId = userDao.insert(userEntity)
        val insertedUser = userDao.selectBy(insertedId)

        assertThat(insertedUser).isEqualTo(userEntity)
    }
}