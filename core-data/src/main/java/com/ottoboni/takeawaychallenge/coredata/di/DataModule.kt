package com.ottoboni.takeawaychallenge.coredata.di

import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantExternalDataSource
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantExternalDataSourceImpl
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantLocalDataSource
import com.ottoboni.takeawaychallenge.coredata.datasource.RestaurantLocalDataSourceImpl
import com.ottoboni.takeawaychallenge.coredata.domain.dispatchers.DispatcherMap
import com.ottoboni.takeawaychallenge.coredata.domain.dispatchers.MainDispatcherMap
import com.ottoboni.takeawaychallenge.coredata.domain.factory.OpeningStatusFactory
import com.ottoboni.takeawaychallenge.coredata.domain.factory.RestaurantFactory
import com.ottoboni.takeawaychallenge.coredata.domain.factory.SortingValuesFactory
import com.ottoboni.takeawaychallenge.coredata.domain.mapper.RestaurantMapper
import com.ottoboni.takeawaychallenge.coredata.domain.mapper.SortingValuesMapper
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
                userRestaurantDao = get(),
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