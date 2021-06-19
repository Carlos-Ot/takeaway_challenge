package com.example.coredata.datasource

import com.example.coredata.domain.factory.RestaurantFactory
import com.example.coredata.domain.model.Restaurant
import com.ottoboni.corelocalstorage.filestore.JsonReader

class RestaurantExternalDataSourceImpl(
    private val jsonReader: JsonReader,
    private val restaurantFactory: RestaurantFactory,
) : RestaurantExternalDataSource {

    override suspend fun getRestaurants(): List<Restaurant>? = runCatching {
        jsonReader.readRestaurantData(SAMPLE_DATA_FILE_NAME)
            ?.restaurants
            ?.ifEmpty { null }
            ?.map(restaurantFactory::make)
    }.getOrNull()

    companion object {
        private const val SAMPLE_DATA_FILE_NAME = "sample_data.json"
    }
}