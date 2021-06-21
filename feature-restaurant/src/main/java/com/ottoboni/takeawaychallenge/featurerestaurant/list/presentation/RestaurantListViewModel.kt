package com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.takeawaychallenge.coredomain.repository.RestaurantRepository
import com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation.SortingOption.*
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val restaurantRepository: RestaurantRepository,
    private val restaurantListMediator: RestaurantListMediator,
) : ViewModel(), LifecycleObserver {

    private val _areFiltersVisible = MutableLiveData(false)
    val areFiltersVisible: LiveData<Boolean> get() = _areFiltersVisible

    private val _selectedFilter = MutableLiveData(BEST_MATCH)
    val selectedFilter: LiveData<SortingOption> get() = _selectedFilter

    private var currentSorting = getComparatorBy(BEST_MATCH)

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = _restaurants

    private val onToggleFavoriteObserver = Observer<Unit> { loadData() }

    init {
        restaurantListMediator.onToggleFavoriteItem.observeForever(onToggleFavoriteObserver)
    }

    fun onSortingButtonClicked() {
        _areFiltersVisible.postValue(_areFiltersVisible.value?.not() ?: false)
    }

    fun onSortingOptionChecked(option: SortingOption) {
        _selectedFilter.postValue(option)
        currentSorting = getComparatorBy(option)
        _restaurants.postValue(
            _restaurants.value?.sortedWith(getCurrentSorting())
        )
    }

    private fun getComparatorBy(option: SortingOption) = when (option) {
        BEST_MATCH -> compareBy<Restaurant> { it.sortingValues?.bestMatch }
        NEWEST -> compareByDescending { it.sortingValues?.newest }
        RATING_AVERAGE -> compareBy { it.sortingValues?.ratingAverage }
        DISTANCE -> compareByDescending { it.sortingValues?.distance }
        POPULARITY -> compareBy { it.sortingValues?.popularity }
        AVERAGE_PRODUCT_PRICE -> compareByDescending { it.sortingValues?.averageProductPrice }
        DELIVERY_COSTS -> compareByDescending { it.sortingValues?.deliveryCosts }
        MIN_COST -> compareByDescending { it.sortingValues?.minCost }
    }

    private fun getCurrentSorting() =
        compareBy<Restaurant> { it.isFavorite }
            .thenBy { it.status == OpeningStatus.OPEN }
            .thenBy { it.status == OpeningStatus.ORDER_AHEAD }
            .thenBy { it.status == OpeningStatus.CLOSED }
            .then(currentSorting)
            .reversed()

    override fun onCleared() {
        restaurantListMediator.onToggleFavoriteItem.removeObserver(onToggleFavoriteObserver)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        val restaurants = restaurantRepository.getRestaurants()
            .sortedWith(getCurrentSorting())
        _restaurants.postValue(restaurants)
    }
}
