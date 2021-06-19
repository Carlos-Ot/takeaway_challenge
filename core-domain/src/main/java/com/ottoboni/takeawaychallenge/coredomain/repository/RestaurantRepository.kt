package com.ottoboni.takeawaychallenge.coredomain.repository

import androidx.lifecycle.LiveData
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant

interface RestaurantRepository {

    suspend fun getRestaurants(): List<Restaurant>

    suspend fun toggleFavoriteStatusFor(restaurant: Restaurant): Boolean

    fun observeFavoriteStatusFor(restaurant: Restaurant): LiveData<Boolean>
}