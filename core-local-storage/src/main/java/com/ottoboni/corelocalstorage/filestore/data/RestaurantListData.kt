package com.ottoboni.corelocalstorage.filestore.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RestaurantListData(
    val restaurants: List<RestaurantData>
)