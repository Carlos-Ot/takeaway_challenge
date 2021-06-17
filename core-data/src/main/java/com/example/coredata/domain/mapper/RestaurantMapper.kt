package com.example.coredata.domain.mapper

import com.example.coredata.domain.model.Restaurant
import com.ottoboni.corelocalstorage.database.entity.RestaurantEntity

class RestaurantMapper : Mapper<RestaurantEntity, Restaurant> {
    override fun toDomain(entity: RestaurantEntity) = Restaurant(
        name = entity.name,
        isFavorite = false,
        status = null,
        sortingValues = null
    )

    override fun fromDomain(domain: Restaurant) = RestaurantEntity(name = domain.name)
}