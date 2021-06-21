package com.ottoboni.takeawaychallenge.featurerestaurant.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.coredomain.repository.RestaurantRepository
import com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation.RestaurantListMediator
import com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation.RestaurantListViewModel
import com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation.SortingOption
import com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation.SortingOption.DELIVERY_COSTS
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RestaurantListViewModelTest {

    @get:Rule
    var executorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var restaurantRepository: RestaurantRepository

    @MockK
    private lateinit var restaurantListMediator: RestaurantListMediator

    @RelaxedMockK
    private lateinit var areFiltersVisibleObserver: Observer<Boolean>

    @RelaxedMockK
    private lateinit var selectedFilterObserver: Observer<SortingOption>

    @RelaxedMockK
    private lateinit var restaurantsObserver: Observer<List<Restaurant>>

    private lateinit var viewModel: RestaurantListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { restaurantListMediator.onToggleFavoriteItem } returns MutableLiveData()

        viewModel = RestaurantListViewModel(restaurantRepository, restaurantListMediator)
    }

    @Test
    fun `test should trigger action when sortingButton is clicked`() {
        viewModel.areFiltersVisible.observeForever(areFiltersVisibleObserver)

        viewModel.onSortingButtonClicked()

        verify(exactly = 1) { areFiltersVisibleObserver.onChanged(true) }
    }

    @Test
    fun `test should update sorting parameters when sorting option changed`() {
        viewModel.selectedFilter.observeForever(selectedFilterObserver)
        viewModel.restaurants.observeForever(restaurantsObserver)

        viewModel.onSortingOptionChecked(DELIVERY_COSTS)

        verify(exactly = 1) { selectedFilterObserver.onChanged(DELIVERY_COSTS) }
        verify(atLeast = 1) { restaurantsObserver.onChanged(any()) }
    }
}
