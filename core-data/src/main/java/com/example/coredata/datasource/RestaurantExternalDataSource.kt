package com.example.coredata.datasource

import com.example.coredata.domain.model.Restaurant

interface RestaurantExternalDataSource {

    suspend fun getRestaurants(): List<Restaurant>?
}