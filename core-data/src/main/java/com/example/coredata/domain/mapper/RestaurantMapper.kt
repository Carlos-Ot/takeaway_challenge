package com.example.coredata.domain.mapper

import com.example.coredata.domain.model.Restaurant
import com.example.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.corelocalstorage.database.entity.RestaurantEntity
import java.lang.Exception

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