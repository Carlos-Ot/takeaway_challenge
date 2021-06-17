package com.example.coredata.domain.model

data class SortingValues(
    val bestMatch: Float,
    val newest: Float,
    val ratingAverage: Float,
    val distance: Long,
    val popularity: Float,
    val averageProductPrice: Long,
    val deliveryCosts: Long,
    val minCost: Long
)
