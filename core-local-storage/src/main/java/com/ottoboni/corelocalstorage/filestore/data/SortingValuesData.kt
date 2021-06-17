package com.ottoboni.corelocalstorage.filestore.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SortingValuesData(
    val bestMatch: Double,
    val newest: Double,
    val ratingAverage: Double,
    val distance: Long,
    val popularity: Double,
    val averageProductPrice: Long,
    val deliveryCosts: Long,
    val minCost: Long
)
