package com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.takeawaychallenge.coredomain.repository.RestaurantRepository
import com.ottoboni.takeawaychallenge.shared.livedata.SingleLiveEvent
import kotlinx.coroutines.launch

class RestaurantItemViewModel(
    private val restaurant: Restaurant,
    private val restaurantRepository: RestaurantRepository,
    private val restaurantListMediator: RestaurantListMediator,
) : ViewModel() {

    val name: String get() = restaurant.name.orEmpty()
    val openingStatus: OpeningStatus get() = restaurant.status
    val averageRating: String
        get() = restaurant.sortingValues?.ratingAverage?.toString() ?: DEFAULT_STRING
    val distance: Float
        get() = restaurant.sortingValues?.distance?.div(KILOMETERS_DIVISOR) ?: DEFAULT_FLOAT
    val minCost: Float
        get() = restaurant.sortingValues?.minCost?.div(CENTS_DIVISOR) ?: DEFAULT_FLOAT
    val deliveryCosts: Float
        get() = restaurant.sortingValues?.deliveryCosts?.div(CENTS_DIVISOR) ?: DEFAULT_FLOAT

    val isFavorite = restaurantRepository.observeFavoriteStatusFor(restaurant)

    private val _onItemClicked = SingleLiveEvent<Unit>()
    val onItemClicked: LiveData<Unit> get() = _onItemClicked

    fun onItemClicked() = _onItemClicked.call()

    fun onBookmarkButtonClicked() {
        viewModelScope.launch {
            restaurant.isFavorite = restaurantRepository.toggleFavoriteStatusFor(restaurant)
            restaurantListMediator.onToggleFavoriteItem()
        }
    }

    companion object {
        private const val DEFAULT_STRING = "0.0"
        private const val DEFAULT_FLOAT = 0.0f
        private const val KILOMETERS_DIVISOR = 1000f
        private const val CENTS_DIVISOR = 100f
    }
}
