package com.ottoboni.takeawaychallenge.coredomain.repository

import androidx.lifecycle.LiveData
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantExternalDataSource
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantLocalDataSource
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant

class RestaurantRepositoryImpl(
    private val localDataSource: RestaurantLocalDataSource,
    private val externalDataSource: RestaurantExternalDataSource,
) : RestaurantRepository {

    override suspend fun getRestaurants(): List<Restaurant> =
        localDataSource
            .getAll()
            ?.plus(externalDataSource.getRestaurants() ?: emptyList())
            ?.distinctBy { it.name }
            ?: externalDataSource.getRestaurants()
            ?: emptyList()

    override suspend fun toggleFavoriteStatusFor(restaurant: Restaurant): Boolean =
        localDataSource.toggleFavoriteStatusFor(restaurant = restaurant)

    override fun observeFavoriteStatusFor(restaurant: Restaurant): LiveData<Boolean> =
        localDataSource.observeFavoriteStatusFor(restaurant = restaurant)
}