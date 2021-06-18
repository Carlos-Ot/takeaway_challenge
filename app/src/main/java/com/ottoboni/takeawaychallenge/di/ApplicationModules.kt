package com.ottoboni.takeawaychallenge.di

import com.example.coredata.di.DataModule
import com.ottoboni.corelocalstorage.di.LocalStorageModule

object ApplicationModules {

    private val coreModules = listOf(
        LocalStorageModule.instance,
        DataModule.instance
    )

    val modules = coreModules
}
