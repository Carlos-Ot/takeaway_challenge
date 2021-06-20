package com.ottoboni.takeawaychallenge.featurerestaurant.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.coredata.domain.model.SortingValues
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.takeawaychallenge.coredomain.repository.RestaurantRepository
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel(
    private val restaurant: Restaurant,
    private val restaurantRepository: RestaurantRepository,
) : ViewModel() {

    val name: String get() = restaurant.name.orEmpty()
    val openingStatus: OpeningStatus get() = restaurant.status

    private val sortingValues: SortingValues? get() = restaurant.sortingValues
    val averageRating: Float get() = sortingValues?.ratingAverage ?: DEFAULT_FLOAT
    val distance: Float get() = sortingValues?.distance?.div(KILOMETERS_DIVISOR) ?: DEFAULT_FLOAT
    val minCost: Float get() = sortingValues?.minCost?.div(CENTS_DIVISOR) ?: DEFAULT_FLOAT
    val deliveryCosts: Float
        get() = sortingValues?.deliveryCosts?.div(CENTS_DIVISOR) ?: DEFAULT_FLOAT
    val newest: Float get() = sortingValues?.newest ?: DEFAULT_FLOAT
    val bestMatch: Float get() = sortingValues?.bestMatch ?: DEFAULT_FLOAT
    val popularity: Float get() = sortingValues?.popularity ?: DEFAULT_FLOAT
    val avgProductPrice: Float
        get() = sortingValues?.averageProductPrice?.div(CENTS_DIVISOR) ?: DEFAULT_FLOAT

    val isFavorite = restaurantRepository.observeFavoriteStatusFor(restaurant)

    fun onBookmarkButtonClicked() {
        viewModelScope.launch {
            restaurantRepository.toggleFavoriteStatusFor(restaurant)
        }
    }

    companion object {
        private const val DEFAULT_FLOAT = 0.0f
        private const val KILOMETERS_DIVISOR = 1000f
        private const val CENTS_DIVISOR = 100f
    }
}
