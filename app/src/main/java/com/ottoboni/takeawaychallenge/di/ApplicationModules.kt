package com.ottoboni.takeawaychallenge.di

import com.ottoboni.corelocalstorage.di.LocalStorageModule

object ApplicationModules {

    private val coreModules = listOf(
        LocalStorageModule.instance
    )

    val modules = coreModules
}
