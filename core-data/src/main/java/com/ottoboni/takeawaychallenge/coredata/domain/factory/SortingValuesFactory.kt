package com.ottoboni.takeawaychallenge.coredata.domain.factory

import com.ottoboni.takeawaychallenge.coredata.domain.model.SortingValues
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.SortingValuesData

class SortingValuesFactory : ModelFactory<SortingValuesData, SortingValues> {
    override fun make(input: SortingValuesData) = SortingValues(
        bestMatch = input.bestMatch ?: DEFAULT_FLOAT,
        newest = input.newest ?: DEFAULT_FLOAT,
        ratingAverage = input.ratingAverage ?: DEFAULT_FLOAT,
        distance = input.distance ?: DEFAULT_LONG,
        popularity = input.popularity ?: DEFAULT_FLOAT,
        averageProductPrice = input.averageProductPrice ?: DEFAULT_LONG,
        deliveryCosts = input.deliveryCosts ?: DEFAULT_LONG,
        minCost = input.minCost ?: DEFAULT_LONG
    )

    companion object {
        private const val DEFAULT_FLOAT = 0.0F
        private const val DEFAULT_LONG = 0L
    }
}
