package com.example.coredata.di

import com.example.coredata.datasource.RestaurantExternalDataSource
import com.example.coredata.datasource.RestaurantExternalDataSourceImpl
import com.example.coredata.datasource.RestaurantLocalDataSource
import com.example.coredata.datasource.RestaurantLocalDataSourceImpl
import com.example.coredata.domain.dispatchers.DispatcherMap
import com.example.coredata.domain.dispatchers.MainDispatcherMap
import com.example.coredata.domain.factory.OpeningStatusFactory
import com.example.coredata.domain.factory.RestaurantFactory
import com.example.coredata.domain.factory.SortingValuesFactory
import com.example.coredata.domain.mapper.RestaurantMapper
import com.example.coredata.domain.mapper.SortingValuesMapper
import org.koin.dsl.module

object DataModule {

    val instance = module {
        factory<DispatcherMap> { MainDispatcherMap() }

        factory { OpeningStatusFactory() }

        factory { SortingValuesFactory() }

        factory {
            RestaurantFactory(
                openingStatusFactory = get(),
                sortingValuesFactory = get()
            )
        }

        factory { SortingValuesMapper() }

        factory { RestaurantMapper(sortingValuesMapper = get()) }

        factory<RestaurantLocalDataSource> {
            RestaurantLocalDataSourceImpl(
                restaurantDao = get(),
                restaurantMapper = get()
            )
        }

        factory<RestaurantExternalDataSource> {
            RestaurantExternalDataSourceImpl(
                jsonReader = get(),
                restaurantFactory = get()
            )
        }
    }
}