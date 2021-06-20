package com.ottoboni.takeawaychallenge.coredata.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SortingValues(
    val bestMatch: Float,
    val newest: Float,
    val ratingAverage: Float,
    val distance: Long,
    val popularity: Float,
    val averageProductPrice: Long,
    val deliveryCosts: Long,
    val minCost: Long,
) : Parcelable
