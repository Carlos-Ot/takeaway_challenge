package com.ottoboni.corelocalstorage.filestore

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class JsonReader(val context: Context, val moshi: Moshi) {

    inline fun <reified T : Any> readJsonDataFromFile(fileName: String): T? {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)

        val jsonData = context.assets.open(fileName).bufferedReader().use { it.readText() }

        return jsonAdapter.fromJson(jsonData)
    }
}
