package com.ottoboni.takeawaychallenge.coredata.domain.factory

import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.coredata.domain.model.SortingValues
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.RestaurantData
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.SortingValuesData

class RestaurantFactory(
    private val openingStatusFactory: OpeningStatusFactory,
    private val sortingValuesFactory: SortingValuesFactory,
) : ModelFactory<RestaurantData, Restaurant> {
    override fun make(input: RestaurantData) = Restaurant(
        name = input.name?.ifBlank { null },
        status = input.status?.let(openingStatusFactory::make) ?: OpeningStatus.CLOSED,
        isFavorite = false,
        sortingValues = input.sortingValues?.let(sortingValuesFactory::make)
    )
}