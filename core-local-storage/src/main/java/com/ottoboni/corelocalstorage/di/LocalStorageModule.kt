package com.ottoboni.corelocalstorage.di

import com.ottoboni.corelocalstorage.database.AppDatabase
import org.koin.dsl.module

object LocalStorageModule {

    val instance = module {
        single { AppDatabase.buildDatabase(context = get()) }

        factory { get<AppDatabase>().restaurantDao() }

        factory { get<AppDatabase>().userDao() }

        factory { get<AppDatabase>().userRestaurantDao() }
    }
}