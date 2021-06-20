package com.ottoboni.takeawaychallenge.coredomain.di

import com.ottoboni.takeawaychallenge.coredomain.repository.RestaurantRepository
import com.ottoboni.takeawaychallenge.coredomain.repository.RestaurantRepositoryImpl
import org.koin.dsl.module

object DomainModule {
    val instance = module {
        factory<RestaurantRepository> {
            RestaurantRepositoryImpl(
                localDataSource = get(),
                externalDataSource = get()
            )
        }
    }
}
