package com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.takeawaychallenge.coredomain.repository.RestaurantRepository
import kotlinx.coroutines.launch

class RestaurantItemViewModel(
    private val restaurant: Restaurant,
    private val restaurantRepository: RestaurantRepository,
) : ViewModel() {

    val name: String get() = restaurant.name.orEmpty()
    val openingStatus: OpeningStatus get() = restaurant.status
    val averageRating: String get() = restaurant.sortingValues?.ratingAverage?.toString() ?: "0.0"
    val distance: Float get() = restaurant.sortingValues?.distance?.div(1000f) ?: 0.0f
    val minCost: Float get() = restaurant.sortingValues?.minCost?.div(100f) ?: 0.0f
    val deliveryCosts: Float get() = restaurant.sortingValues?.deliveryCosts?.div(100f) ?: 0.0f

    val isFavorite = restaurantRepository.observeFavoriteStatusFor(restaurant)

    fun onItemClicked() = Unit

    fun onBookmarkButtonClicked() {
        viewModelScope.launch {
            restaurantRepository.toggleFavoriteStatusFor(restaurant)
        }
    }
}