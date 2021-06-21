package com.ottoboni.takeawaychallenge.featurerestaurant.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.ottoboni.takeawaychallenge.coredomain.repository.RestaurantRepository
import com.ottoboni.takeawaychallenge.featurerestaurant.CENTS_DIVISOR
import com.ottoboni.takeawaychallenge.featurerestaurant.DEFAULT_FLOAT
import com.ottoboni.takeawaychallenge.featurerestaurant.DEFAULT_STRING
import com.ottoboni.takeawaychallenge.featurerestaurant.EMPTY_STRING
import com.ottoboni.takeawaychallenge.featurerestaurant.KILOMETERS_DIVISOR
import com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation.RestaurantItemViewModel
import com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation.RestaurantListMediator
import com.ottoboni.takeawaychallenge.featurerestaurant.mockRestaurant
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.justRun
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RestaurantItemViewModelTest {

    @get:Rule
    var executorRule = InstantTaskExecutorRule()

    private val restaurant = mockRestaurant()

    @MockK
    private lateinit var restaurantRepository: RestaurantRepository

    @MockK
    private lateinit var restaurantListMediator: RestaurantListMediator

    @RelaxedMockK
    private lateinit var onItemClickedObserver: Observer<Unit>

    private lateinit var viewModel: RestaurantItemViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { restaurantRepository.observeFavoriteStatusFor(any()) } returns MutableLiveData(false)

        viewModel = RestaurantItemViewModel(
            restaurant,
            restaurantRepository,
            restaurantListMediator
        )
    }

    @Test
    fun `test restaurant fields should be formatted`() {
        assertThat(viewModel.name).isEqualTo(restaurant.name)
        assertThat(viewModel.openingStatus).isEqualTo(restaurant.status)
        assertThat(viewModel.averageRating).isEqualTo(
            restaurant.sortingValues?.ratingAverage.toString()
        )
        assertThat(viewModel.distance).isEqualTo(
            restaurant.sortingValues?.distance?.div(KILOMETERS_DIVISOR)
        )
        assertThat(viewModel.minCost).isEqualTo(
            restaurant.sortingValues?.minCost?.div(CENTS_DIVISOR)
        )
        assertThat(viewModel.deliveryCosts).isEqualTo(
            restaurant.sortingValues?.deliveryCosts?.div(CENTS_DIVISOR))
    }

    @Test
    fun `test fields should be default when sortingValues is null`() {
        every { restaurant.name } returns null
        every { restaurant.sortingValues } returns null

        assertThat(viewModel.name).isEqualTo(EMPTY_STRING)
        assertThat(viewModel.averageRating).isEqualTo(DEFAULT_STRING)
        assertThat(viewModel.distance).isEqualTo(DEFAULT_FLOAT)
        assertThat(viewModel.minCost).isEqualTo(DEFAULT_FLOAT)
        assertThat(viewModel.deliveryCosts).isEqualTo(DEFAULT_FLOAT)
    }

    @Test
    fun `test should trigger action when clicking on item`() {
        viewModel.onItemClicked.observeForever(onItemClickedObserver)

        viewModel.onItemClicked()

        verify(exactly = 1) { onItemClickedObserver.onChanged(null) }
    }

    @Test
    fun `test should update favorite status when clicking on item`() = runBlockingTest {
        coEvery { restaurantRepository.toggleFavoriteStatusFor(any()) } returns true
        justRun { restaurantListMediator.onToggleFavoriteItem() }

        viewModel.onBookmarkButtonClicked()

        coVerify(exactly = 1) { restaurantRepository.toggleFavoriteStatusFor(restaurant) }
        verify(exactly = 1) { restaurantListMediator.onToggleFavoriteItem() }
    }
}