package com.example.coredomain.repository

import androidx.lifecycle.LiveData
import com.example.coredata.domain.model.Restaurant

interface RestaurantRepository {

    suspend fun getRestaurants(): List<Restaurant>?

    suspend fun toggleFavoriteStatusFor(restaurant: Restaurant): Boolean

    fun observeFavoriteStatusFor(id: Long): LiveData<Boolean>
}