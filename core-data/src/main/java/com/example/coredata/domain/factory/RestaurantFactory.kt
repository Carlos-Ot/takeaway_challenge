package com.example.coredata.domain.factory

import com.example.coredata.domain.model.Restaurant
import com.example.coredata.domain.model.SortingValues
import com.example.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.corelocalstorage.filestore.data.RestaurantData
import com.ottoboni.corelocalstorage.filestore.data.SortingValuesData

class RestaurantFactory(
    private val openingStatusFactory: OpeningStatusFactory,
    private val sortingValuesFactory: ModelFactory<SortingValuesData, SortingValues>,
) : ModelFactory<RestaurantData, Restaurant> {
    override fun make(input: RestaurantData) = Restaurant(
        name = input.name?.ifBlank { null },
        status = input.status?.let(openingStatusFactory::make) ?: OpeningStatus.CLOSED,
        isFavorite = false,
        sortingValues = input.sortingValues?.let(sortingValuesFactory::make)
    )
}