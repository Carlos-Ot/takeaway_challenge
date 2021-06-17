package com.ottoboni.corelocalstorage.filestore.data

import com.ottoboni.corelocalstorage.filestore.data.enums.OpeningStatusData
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestaurantData(
    val name: String?,
    val status: OpeningStatusData?,
    val sortingValues: SortingValuesData?
)
