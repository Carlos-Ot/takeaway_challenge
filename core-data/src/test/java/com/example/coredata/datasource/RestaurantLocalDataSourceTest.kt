package com.example.coredata.datasource

import com.example.coredata.Shared.mockRestaurant
import com.example.coredata.Shared.mockRestaurantEntity
import com.example.coredata.Shared.mockRestaurantEntityList
import com.example.coredata.domain.mapper.RestaurantMapper
import com.google.common.truth.Truth.assertThat
import com.ottoboni.corelocalstorage.database.dao.RestaurantDao
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantLocalDataSourceTest {

    @MockK
    private lateinit var restaurantDao: RestaurantDao

    @MockK
    private lateinit var restaurantMapper: RestaurantMapper

    @InjectMockKs
    private lateinit var dataSource: RestaurantLocalDataSourceImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getAll should return non-empty list`() = runBlockingTest {
        val listSize = 5
        coEvery { restaurantDao.selectAll() } returns mockRestaurantEntityList(listSize)
        every { restaurantMapper.toDomain(any()) } returns mockRestaurant()

        val restaurants = dataSource.getAll()

        coVerify(exactly = 1) { restaurantDao.selectAll() }
        verify(exactly = listSize) { restaurantMapper.toDomain(any()) }
        assertThat(restaurants).isNotNull()
        assertThat(restaurants).isNotEmpty()
        assertThat(restaurants?.size).isEqualTo(listSize)
    }

    @Test
    fun `test getAll should return null for empty list`() = runBlockingTest {
        coEvery { restaurantDao.selectAll() } returns mockRestaurantEntityList(0)
        every { restaurantMapper.toDomain(any()) } returns mockRestaurant()

        val restaurants = dataSource.getAll()

        coVerify(exactly = 1) { restaurantDao.selectAll() }
        verify(exactly = 0) { restaurantMapper.toDomain(any()) }
        assertThat(restaurants).isNull()
    }

    @Test
    fun `test getAll should be null when result is null`() = runBlockingTest {
        coEvery { restaurantDao.selectAll() } returns null
        every { restaurantMapper.toDomain(any()) } returns mockRestaurant()

        val restaurants = dataSource.getAll()

        coVerify(exactly = 1) { restaurantDao.selectAll() }
        verify(exactly = 0) { restaurantMapper.toDomain(any()) }
        assertThat(restaurants).isNull()
    }

    @Test
    fun `test getBy should return a valid Restaurant`() = runBlockingTest {
        coEvery { restaurantDao.selectBy(any()) } returns mockRestaurantEntity()
        every { restaurantMapper.toDomain(any()) } returns mockRestaurant()

        val restaurant = dataSource.getBy(0)

        coVerify(exactly = 1) { restaurantDao.selectBy(any()) }
        verify(exactly = 1) { restaurantMapper.toDomain(any()) }
        assertThat(restaurant).isNotNull()
    }

    @Test
    fun `test getBy should return null`() = runBlockingTest {
        coEvery { restaurantDao.selectBy(any()) } returns null
        every { restaurantMapper.toDomain(any()) } returns mockRestaurant()

        val restaurant = dataSource.getBy(0)

        coVerify(exactly = 1) { restaurantDao.selectBy(any()) }
        verify(exactly = 0) { restaurantMapper.toDomain(any()) }
        assertThat(restaurant).isNull()
    }

    @Test
    fun `test save should be success`() = runBlockingTest {
        val mockId = 1L
        coEvery { restaurantDao.insert(any()) } returns mockId
        every { restaurantMapper.fromDomain(any()) } returns mockRestaurantEntity()

        val insertedId = dataSource.save(mockRestaurant())

        coVerify(exactly = 1) { restaurantDao.insert(any()) }
        verify(exactly = 1) { restaurantMapper.fromDomain(any()) }
        assertThat(insertedId).isEqualTo(mockId)
    }

    @Test
    fun `test deleteBy should be success`() = runBlockingTest {
        coJustRun { restaurantDao.deleteSafelyBy(any()) }

        restaurantDao.deleteSafelyBy(1)

        coVerify(exactly = 1) { restaurantDao.deleteSafelyBy(any()) }
    }
}