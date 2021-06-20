package com.ottoboni.takeawaychallenge.featurerestaurant.details.di

import com.ottoboni.takeawaychallenge.featurerestaurant.details.presentation.RestaurantDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object RestaurantDetailsModule {

    val instance = module {
        viewModel { params ->
            RestaurantDetailsViewModel(
                restaurant = params.get(),
                restaurantRepository = get()
            )
        }
    }
}
