package com.example.coredata.domain.mapper

import com.example.coredata.domain.model.SortingValues
import com.ottoboni.corelocalstorage.database.entity.SortingValuesEntity

class SortingValuesMapper : Mapper<SortingValuesEntity, SortingValues> {
    override fun toDomain(entity: SortingValuesEntity) = SortingValues(
        bestMatch = entity.bestMatch,
        newest = entity.newest,
        ratingAverage = entity.ratingAverage,
        distance = entity.distance,
        popularity = entity.popularity,
        averageProductPrice = entity.averageProductPrice,
        deliveryCosts = entity.deliveryCosts,
        minCost = entity.minCost
    )

    override fun fromDomain(domain: SortingValues) = SortingValuesEntity(
        bestMatch = domain.bestMatch,
        newest = domain.newest,
        ratingAverage = domain.ratingAverage,
        distance = domain.distance,
        popularity = domain.popularity,
        averageProductPrice = domain.averageProductPrice,
        deliveryCosts = domain.deliveryCosts,
        minCost = domain.minCost
    )
}