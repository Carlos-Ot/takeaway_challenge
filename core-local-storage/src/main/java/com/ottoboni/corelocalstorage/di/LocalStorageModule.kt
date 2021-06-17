package com.ottoboni.corelocalstorage.di

import com.ottoboni.corelocalstorage.database.AppDatabase
import com.ottoboni.corelocalstorage.filestore.JsonReader
import com.squareup.moshi.Moshi
import org.koin.dsl.module

object LocalStorageModule {

    val instance = module {
        single { AppDatabase.buildDatabase(context = get()) }

        factory { get<AppDatabase>().restaurantDao() }

        factory { get<AppDatabase>().userDao() }

        factory { get<AppDatabase>().userRestaurantDao() }

        factory { provideMoshi() }

        factory { JsonReader(context = get(), moshi = get()) }
    }

    private fun provideMoshi() = Moshi.Builder().build()
}