package com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RestaurantListData(
    val restaurants: List<RestaurantData>?
)
