package com.example.coredomain.datasource

import com.ottoboni.corelocalstorage.filestore.data.RestaurantListData

interface RestaurantLocalDataSource {

    suspend fun getRestaurants(): List<RestaurantListData>

    // TODO: Solution to Merge Favorites list with Read list
    // favorites.union(read).distinctBy { it.name }
}