package com.ottoboni.takeawaychallenge.featurerestaurant

import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import io.mockk.every
import io.mockk.mockk

fun mockRestaurant() = mockk<Restaurant> {
    every { name } returns ""
    every { status } returns OpeningStatus.OPEN
    every { isFavorite } returns true
    every { sortingValues } returns mockk {
        every { bestMatch } returns 10.0f
        every { newest } returns 173.0f
        every { ratingAverage } returns 4.5f
        every { distance } returns 2234
        every { popularity } returns 99.0f
        every { averageProductPrice } returns 14389
        every { deliveryCosts } returns 2000
        every { minCost } returns 12000
    }
}

const val EMPTY_STRING = ""
const val DEFAULT_STRING = "0.0"
const val DEFAULT_FLOAT = 0.0f
const val KILOMETERS_DIVISOR = 1000f
const val CENTS_DIVISOR = 100f
