package com.ottoboni.takeawaychallenge.featurerestaurant.list.di

import com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation.RestaurantItemViewModel
import com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation.RestaurantListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object RestaurantListModule {

    val instance = module {
        viewModel {
            RestaurantListViewModel(
                restaurantRepository = get()
            )
        }

        viewModel { params ->
            RestaurantItemViewModel(
                restaurant = params.get(),
                restaurantRepository = get()
            )
        }
    }
}
