package com.example.coredomain.datasource

import com.ottoboni.corelocalstorage.filestore.data.RestaurantListData

interface RestaurantLocalDataSource {
    suspend fun getRestaurants(): List<RestaurantListData>
}