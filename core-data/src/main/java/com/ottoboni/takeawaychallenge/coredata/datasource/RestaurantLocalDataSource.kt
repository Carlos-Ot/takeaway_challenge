package com.ottoboni.takeawaychallenge.coredata.datasource

import androidx.lifecycle.LiveData
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.corelocalstorage.database.AppDatabase.Companion.GUEST_USER_ID

interface RestaurantLocalDataSource {

    suspend fun getAll(): List<Restaurant>?

    suspend fun getBy(id: Long): Restaurant?

    suspend fun save(restaurant: Restaurant): Long

    suspend fun deleteBy(id: Long)

    suspend fun toggleFavoriteStatusFor(
        userId: Long = GUEST_USER_ID,
        restaurant: Restaurant,
    ): Boolean

    fun observeFavoriteStatusFor(
        userId: Long = GUEST_USER_ID,
        restaurant: Restaurant,
    ): LiveData<Boolean>
}
