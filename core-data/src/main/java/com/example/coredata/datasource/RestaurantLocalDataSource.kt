package com.example.coredata.datasource

import com.example.coredata.domain.model.Restaurant

interface RestaurantLocalDataSource {

    suspend fun getAll(): List<Restaurant>?

    suspend fun getBy(id: Long): Restaurant?

    suspend fun save(restaurant: Restaurant): Long

    suspend fun deleteBy(id: Long)
}