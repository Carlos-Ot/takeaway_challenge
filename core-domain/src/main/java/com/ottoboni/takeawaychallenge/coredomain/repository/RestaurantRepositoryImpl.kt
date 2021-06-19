package com.ottoboni.takeawaychallenge.coredomain.repository

import androidx.lifecycle.LiveData
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantExternalDataSource
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantLocalDataSource
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant

class RestaurantRepositoryImpl(
    private val localDataSource: RestaurantLocalDataSource,
    private val externalDataSource: RestaurantExternalDataSource
) : RestaurantRepository {
    override suspend fun getRestaurants(): List<Restaurant>? {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavoriteStatusFor(restaurant: Restaurant): Boolean {
        TODO("Not yet implemented")
    }

    override fun observeFavoriteStatusFor(id: Long): LiveData<Boolean> {
        TODO("Not yet implemented")
    }
}