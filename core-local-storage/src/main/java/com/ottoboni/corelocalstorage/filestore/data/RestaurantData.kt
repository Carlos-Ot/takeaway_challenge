package com.ottoboni.corelocalstorage.filestore.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestaurantData(
    val name: String,
    val status: String,
    val sortingValues: SortingValuesData
)
