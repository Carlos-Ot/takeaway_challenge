package com.ottoboni.takeawaychallenge.coredata.domain.mapper

import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.RestaurantEntity

class RestaurantMapper(private val sortingValuesMapper: SortingValuesMapper) :
    Mapper<RestaurantEntity, Restaurant> {
    override fun toDomain(entity: RestaurantEntity) = Restaurant(
        name = entity.name,
        isFavorite = true,
        status = OpeningStatus.safeValueOf(entity.status.uppercase()) ?: OpeningStatus.CLOSED,
        sortingValues = entity.sortingValues?.let(sortingValuesMapper::toDomain)
    )

    override fun fromDomain(domain: Restaurant) = RestaurantEntity(
        name = domain.name ?: "",
        status = domain.status.toString(),
        sortingValues = domain.sortingValues?.let(sortingValuesMapper::fromDomain)
    )
}
