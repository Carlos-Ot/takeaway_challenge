package com.ottoboni.takeawaychallenge.coredata.datasource

import com.google.common.truth.Truth.assertThat
import com.ottoboni.takeawaychallenge.coredata.Shared.mockRestaurant
import com.ottoboni.takeawaychallenge.coredata.Shared.mockRestaurantListData
import com.ottoboni.takeawaychallenge.coredata.domain.factory.RestaurantFactory
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.JsonReader
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import java.io.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantExternalDataSourceTest {

    @RelaxedMockK
    private lateinit var jsonReader: JsonReader

    @MockK
    private lateinit var restaurantFactory: RestaurantFactory

    @InjectMockKs
    private lateinit var dataSource: RestaurantExternalDataSourceImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getRestaurants should return non-empty list`() = runBlockingTest {
        val listSize = 5
        coEvery { jsonReader.readRestaurantData(any()) } returns mockRestaurantListData(listSize)
        every { restaurantFactory.make(any()) } returns mockRestaurant()

        val restaurants = dataSource.getRestaurants()

        coVerify(exactly = 1) { jsonReader.readRestaurantData(any()) }
        verify(exactly = listSize) { restaurantFactory.make(any()) }
        assertThat(restaurants).isNotNull()
        assertThat(restaurants).isNotEmpty()
        assertThat(restaurants?.size).isEqualTo(listSize)
    }

    @Test
    fun `test getRestaurants should be null when restaurantList is empty`() = runBlockingTest {
        coEvery { jsonReader.readRestaurantData(any()) } returns mockRestaurantListData(0)
        every { restaurantFactory.make(any()) } returns mockRestaurant()

        val restaurants = dataSource.getRestaurants()

        coVerify(exactly = 1) { jsonReader.readRestaurantData(any()) }
        verify(exactly = 0) { restaurantFactory.make(any()) }
        assertThat(restaurants).isNull()
    }

    @Test
    fun `test getRestaurants should be null when jsonReader throws an exception`() =
        runBlockingTest {
            coEvery { jsonReader.readRestaurantData(any()) } throws IOException()
            every { restaurantFactory.make(any()) } returns mockRestaurant()

            val restaurants = dataSource.getRestaurants()

            coVerify(exactly = 1) { jsonReader.readRestaurantData(any()) }
            verify(exactly = 0) { restaurantFactory.make(any()) }
            assertThat(restaurants).isNull()
        }
}
