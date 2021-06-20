package com.ottoboni.takeawaychallenge.coredomain

import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.coredata.domain.model.SortingValues
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import io.mockk.every
import io.mockk.mockk

fun mockRestaurantList(amount: Int = 0) =
    mutableListOf<Restaurant>()
        .apply { repeat(amount) { this += mockRestaurant(it) } }

fun mockFavoriteRestaurantList(amount: Int = 0) =
    mutableListOf<Restaurant>()
        .apply { repeat(amount) { this += mockRestaurant(it, true) } }

fun mockRestaurant(id: Int = 0, favorite: Boolean = false) = mockk<Restaurant> {
    every { name } returns "name$id"
    every { status } returns OpeningStatus.OPEN
    every { sortingValues } returns mockSortingValues()
    every { isFavorite } returns favorite
}

private fun mockSortingValues() = mockk<SortingValues> {
    every { bestMatch } returns 11.0f
    every { newest } returns 4.2f
    every { ratingAverage } returns 9.9f
    every { distance } returns 1000
    every { popularity } returns 9.0f
    every { averageProductPrice } returns 1223
    every { deliveryCosts } returns 100
    every { minCost } returns 900
}
