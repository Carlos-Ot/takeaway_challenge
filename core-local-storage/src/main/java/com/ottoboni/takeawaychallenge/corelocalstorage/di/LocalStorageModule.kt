package com.ottoboni.takeawaychallenge.corelocalstorage.di

import com.ottoboni.takeawaychallenge.corelocalstorage.database.AppDatabase
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.RestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserRestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.JsonReader
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.JsonReaderImpl
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.enums.OpeningStatusData
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import org.koin.dsl.module

object LocalStorageModule {

    val instance = module {
        single { AppDatabase.buildDatabase(context = get()) }

        factory { get<AppDatabase>().restaurantDao() }

        factory { get<AppDatabase>().userDao() }

        factory{ get<AppDatabase>().userRestaurantDao() }

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
