package com.ottoboni.takeawaychallenge.featurerestaurant.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import com.ottoboni.takeawaychallenge.coredomain.repository.RestaurantRepository
import com.ottoboni.takeawaychallenge.featurerestaurant.CENTS_DIVISOR
import com.ottoboni.takeawaychallenge.featurerestaurant.DEFAULT_FLOAT
import com.ottoboni.takeawaychallenge.featurerestaurant.EMPTY_STRING
import com.ottoboni.takeawaychallenge.featurerestaurant.KILOMETERS_DIVISOR
import com.ottoboni.takeawaychallenge.featurerestaurant.details.presentation.RestaurantDetailsViewModel
import com.ottoboni.takeawaychallenge.featurerestaurant.mockRestaurant
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantDetailsViewModelTest {

    @get:Rule
    var executorRule = InstantTaskExecutorRule()

    private val restaurant = mockRestaurant()

    @MockK
    private lateinit var restaurantRepository: RestaurantRepository

    private lateinit var viewModel: RestaurantDetailsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { restaurantRepository.observeFavoriteStatusFor(any()) } returns MutableLiveData(true)

        viewModel = RestaurantDetailsViewModel(restaurant, restaurantRepository)
    }

    @Test
    fun `test restaurant fields should be formatted`() {
        assertThat(viewModel.name).isEqualTo(restaurant.name)
        assertThat(viewModel.openingStatus).isEqualTo(restaurant.status)
        assertThat(viewModel.averageRating).isEqualTo(restaurant.sortingValues?.ratingAverage)
        assertThat(viewModel.distance).isEqualTo(
            restaurant.sortingValues?.distance?.div(KILOMETERS_DIVISOR)
        )
        assertThat(viewModel.minCost).isEqualTo(
            restaurant.sortingValues?.minCost?.div(CENTS_DIVISOR)
        )
        assertThat(viewModel.deliveryCosts).isEqualTo(
            restaurant.sortingValues?.deliveryCosts?.div(CENTS_DIVISOR)
        )
        assertThat(viewModel.newest).isEqualTo(restaurant.sortingValues?.newest)
        assertThat(viewModel.bestMatch).isEqualTo(restaurant.sortingValues?.bestMatch)
        assertThat(viewModel.popularity).isEqualTo(restaurant.sortingValues?.popularity)
        assertThat(viewModel.avgProductPrice).isEqualTo(
            restaurant.sortingValues?.averageProductPrice?.div(CENTS_DIVISOR)
        )
    }

    @Test
    fun `test fields should be default when sortingValues is null`() {
        every { restaurant.name } returns null
        every { restaurant.sortingValues } returns null

        assertThat(viewModel.name).isEqualTo(EMPTY_STRING)
        assertThat(viewModel.averageRating).isEqualTo(DEFAULT_FLOAT)
        assertThat(viewModel.distance).isEqualTo(DEFAULT_FLOAT)
        assertThat(viewModel.minCost).isEqualTo(DEFAULT_FLOAT)
        assertThat(viewModel.deliveryCosts).isEqualTo(DEFAULT_FLOAT)
        assertThat(viewModel.newest).isEqualTo(DEFAULT_FLOAT)
        assertThat(viewModel.bestMatch).isEqualTo(DEFAULT_FLOAT)
        assertThat(viewModel.popularity).isEqualTo(DEFAULT_FLOAT)
        assertThat(viewModel.avgProductPrice).isEqualTo(DEFAULT_FLOAT)
    }

    @Test
    fun `test should update favorite status when clicking on item`() = runBlockingTest {
        coEvery { restaurantRepository.toggleFavoriteStatusFor(any()) } returns true

        viewModel.onBookmarkButtonClicked()

        coVerify(exactly = 1) { restaurantRepository.toggleFavoriteStatusFor(restaurant) }
    }
}