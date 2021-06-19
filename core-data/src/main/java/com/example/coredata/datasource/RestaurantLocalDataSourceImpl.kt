package com.example.coredata.datasource

import com.example.coredata.domain.mapper.RestaurantMapper
import com.example.coredata.domain.model.Restaurant
import com.ottoboni.corelocalstorage.database.dao.RestaurantDao

class RestaurantLocalDataSourceImpl(
    private val restaurantDao: RestaurantDao,
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
}