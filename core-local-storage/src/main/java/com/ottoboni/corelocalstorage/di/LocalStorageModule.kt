package com.ottoboni.corelocalstorage.di

import com.ottoboni.corelocalstorage.database.AppDatabase
import com.ottoboni.corelocalstorage.filestore.JsonReader
import com.ottoboni.corelocalstorage.filestore.JsonReaderImpl
import com.ottoboni.corelocalstorage.filestore.data.enums.OpeningStatusData
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import org.koin.dsl.module

object LocalStorageModule {

    val instance = module {
        single { AppDatabase.buildDatabase(context = get()) }

        factory { get<AppDatabase>().restaurantDao() }

        factory { get<AppDatabase>().userDao() }

        factory { get<AppDatabase>().userRestaurantDao() }

        factory { provideMoshi() }

        factory<JsonReader> { JsonReaderImpl(context = get(), moshi = get()) }
    }

    private fun provideMoshi() =
        Moshi.Builder()
            .add(
                OpeningStatusData::class.java,
                EnumJsonAdapter.create(OpeningStatusData::class.java)
                    .withUnknownFallback(OpeningStatusData.CLOSED)
                    .nullSafe()
            )
            .build()
}
