package com.example.coredomain.repository

import androidx.lifecycle.LiveData
import com.example.coredata.datasource.RestaurantExternalDataSource
import com.example.coredata.datasource.RestaurantLocalDataSource
import com.example.coredata.domain.model.Restaurant

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