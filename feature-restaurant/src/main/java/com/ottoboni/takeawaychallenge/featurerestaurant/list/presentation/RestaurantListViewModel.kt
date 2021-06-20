package com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.takeawaychallenge.coredomain.repository.RestaurantRepository
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val restaurantRepository: RestaurantRepository,
) : ViewModel() {

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> get() = _restaurants

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        val comparator =
            compareBy<Restaurant> { it.isFavorite }
                .thenBy { it.status == OpeningStatus.OPEN }
                .thenBy { it.status == OpeningStatus.ORDER_AHEAD }
                .thenBy { it.status == OpeningStatus.CLOSED }
                .thenByDescending { it.sortingValues?.distance }
                .reversed()

        val restaurants = restaurantRepository.getRestaurants()
            .sortedWith(comparator)
        _restaurants.postValue(restaurants)
    }
}