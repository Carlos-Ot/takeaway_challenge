package com.example.coredata.datasource

import androidx.lifecycle.LiveData
import com.example.coredata.domain.mapper.RestaurantMapper
import com.example.coredata.domain.model.Restaurant
import com.ottoboni.corelocalstorage.database.dao.RestaurantDao
import com.ottoboni.corelocalstorage.database.dao.UserRestaurantDao
import com.ottoboni.corelocalstorage.database.entity.UserRestaurantEntity

class RestaurantLocalDataSourceImpl(
    private val restaurantDao: RestaurantDao,
    private val userRestaurantDao: UserRestaurantDao,
    private val restaurantMapper: RestaurantMapper,
) : RestaurantLocalDataSource {

    override suspend fun getAll(): List<Restaurant>? =
        restaurantDao.selectAll()?.ifEmpty { null }?.map(restaurantMapper::toDomain)

    override suspend fun getBy(id: Long): Restaurant? =
        restaurantDao.selectBy(id)?.let(restaurantMapper::toDomain)

    override suspend fun save(restaurant: Restaurant): Long =
        restaurantDao.insert(restaurantMapper.fromDomain(restaurant))

    override suspend fun deleteBy(id: Long) {
        restaurantDao.deleteSafelyBy(id)
    }

    override suspend fun toggleFavoriteStatusFor(userId: Long, restaurant: Restaurant): Boolean {
        val restaurantId = restaurantDao.selectIdBy(restaurant.name ?: "") ?: save(restaurant)

        return userRestaurantDao.selectBy(userId, restaurantId)?.let {
            userRestaurantDao.delete(it)
            restaurantDao.deleteSafelyBy(restaurantId)
            false
        } ?: run {
            userRestaurantDao.insert(UserRestaurantEntity(userId, restaurantId))
            true
        }
    }

    override fun observeFavoriteStatusFor(userId: Long, restaurant: Restaurant) =
        restaurantDao.observeUserRestaurantBy(userId, restaurant.name ?: "")?.
}

