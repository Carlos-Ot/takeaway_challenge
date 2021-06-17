package com.ottoboni.corelocalstorage.filestore.data.enums

import com.squareup.moshi.Json

enum class OpeningStatusData {
    @Json(name = "open")
    OPEN,

    @Json(name = "closed")
    CLOSED,

    @Json(name = "order ahead")
    ORDER_AHEAD
}