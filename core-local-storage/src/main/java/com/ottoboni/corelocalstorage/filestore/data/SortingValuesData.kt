package com.ottoboni.corelocalstorage.filestore.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SortingValuesData(
    val bestMatch: Float?,
    val newest: Float?,
    val ratingAverage: Float?,
    val distance: Long?,
    val popularity: Float?,
    val averageProductPrice: Long?,
    val deliveryCosts: Long?,
    val minCost: Long?,
)
