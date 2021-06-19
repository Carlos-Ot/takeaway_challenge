package com.ottoboni.takeawaychallenge.coredata.datasource

import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant

interface RestaurantExternalDataSource {

    suspend fun getRestaurants(): List<Restaurant>?
}