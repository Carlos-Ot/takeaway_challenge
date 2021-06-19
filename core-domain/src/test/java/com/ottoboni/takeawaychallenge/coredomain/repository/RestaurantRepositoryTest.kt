package com.ottoboni.takeawaychallenge.coredomain.repository

import com.google.common.truth.Truth.assertThat
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantExternalDataSource
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantLocalDataSource
import com.ottoboni.takeawaychallenge.coredomain.mockFavoriteRestaurantList
import com.ottoboni.takeawaychallenge.coredomain.mockRestaurantList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantRepositoryTest {

    @MockK
    private lateinit var localDataSource: RestaurantLocalDataSource

    @MockK
    private lateinit var externalDataSource: RestaurantExternalDataSource

    @InjectMockKs
    private lateinit var repository: RestaurantRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getRestaurants should return combined lists`() = runBlockingTest {
        val favoriteRestaurantSize = 5
        val restaurantSize = 10
        coEvery { localDataSource.getAll() } returns mockFavoriteRestaurantList(
            favoriteRestaurantSize)
        coEvery { externalDataSource.getRestaurants() } returns mockRestaurantList(restaurantSize)

        val restaurants = repository.getRestaurants()

        coVerify(exactly = 1) { localDataSource.getAll() }
        coVerify(exactly = 1) { externalDataSource.getRestaurants() }
        assertThat(restaurants).isNotEmpty()
        assertThat(restaurants.size).isEqualTo(restaurantSize)
        assertThat(restaurants.count { it.isFavorite }).isEqualTo(favoriteRestaurantSize)
        assertThat(restaurants).containsNoDuplicates()
    }

    @Test
    fun `test getRestaurants should return non-favorite restaurants only`() = runBlockingTest {
        val restaurantSize = 10
        coEvery { localDataSource.getAll() } returns null
        coEvery { externalDataSource.getRestaurants() } returns mockRestaurantList(restaurantSize)

        val restaurants = repository.getRestaurants()

        coVerify(exactly = 1) { localDataSource.getAll() }
        coVerify(exactly = 1) { externalDataSource.getRestaurants() }
        assertThat(restaurants).isNotEmpty()
        assertThat(restaurants.size).isEqualTo(restaurantSize)
        assertThat(restaurants.count { it.isFavorite }).isEqualTo(0)
    }

    @Test
    fun `test getRestaurants should return favorite restaurants only`() = runBlockingTest {
        val restaurantSize = 10
        coEvery { localDataSource.getAll() } returns mockFavoriteRestaurantList(restaurantSize)
        coEvery { externalDataSource.getRestaurants() } returns null

        val restaurants = repository.getRestaurants()

        coVerify(exactly = 1) { localDataSource.getAll() }
        coVerify(exactly = 1) { externalDataSource.getRestaurants() }
        assertThat(restaurants).isNotEmpty()
        assertThat(restaurants.size).isEqualTo(restaurantSize)
        assertThat(restaurants.count { !it.isFavorite }).isEqualTo(0)
    }

    @Test
    fun `test getRestaurants should return emptyList`() = runBlockingTest {
        coEvery { localDataSource.getAll() } returns null
        coEvery { externalDataSource.getRestaurants() } returns null

        val restaurants = repository.getRestaurants()
        coVerify(exactly = 1) { localDataSource.getAll() }
        coVerify(exactly = 1) { externalDataSource.getRestaurants() }
        assertThat(restaurants).isEmpty()
    }
}