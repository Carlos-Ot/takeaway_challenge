package com.ottoboni.takeawaychallenge.corelocalstorage.filestore

import android.content.Context
import android.content.res.AssetManager.ACCESS_BUFFER
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.RestaurantListData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

interface JsonReader {
    suspend fun readRestaurantData(fileName: String): RestaurantListData?
}

class JsonReaderImpl(val context: Context, val moshi: Moshi) : JsonReader {

    override suspend fun readRestaurantData(fileName: String): RestaurantListData? {
        val jsonAdapter: JsonAdapter<RestaurantListData> =
            moshi.adapter(RestaurantListData::class.java)

        val jsonData =
            context.assets.open(fileName, ACCESS_BUFFER).bufferedReader().use { it.readText() }

        return jsonAdapter.fromJson(jsonData)
    }
}
