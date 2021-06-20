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
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val restaurantRepository: RestaurantRepository,
    private val restaurantListMediator: RestaurantListMediator,
) : ViewModel(), LifecycleObserver {

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> get() = _restaurants

    private val onToggleFavoriteObserver = Observer<Unit> {
        loadData()
    }

    private val comparator =
        compareBy<Restaurant> { it.isFavorite }
            .thenBy { it.status == OpeningStatus.OPEN }
            .thenBy { it.status == OpeningStatus.ORDER_AHEAD }
            .thenBy { it.status == OpeningStatus.CLOSED }
            .thenByDescending { it.sortingValues?.distance }
            .reversed()

    init {
        restaurantListMediator.onToggleFavoriteItem.observeForever(onToggleFavoriteObserver)
    }

    override fun onCleared() {
        restaurantListMediator.onToggleFavoriteItem.removeObserver(onToggleFavoriteObserver)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        val restaurants = restaurantRepository.getRestaurants()
            .sortedWith(comparator)
        _restaurants.postValue(restaurants)
    }
}
