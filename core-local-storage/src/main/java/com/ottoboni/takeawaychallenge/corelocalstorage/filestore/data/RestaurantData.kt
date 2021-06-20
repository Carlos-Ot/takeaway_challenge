package com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data

import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.enums.OpeningStatusData
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestaurantData(
    val name: String?,
    val status: OpeningStatusData?,
    val sortingValues: SortingValuesData?
)
