package com.ottoboni.takeawaychallenge.di

import com.ottoboni.takeawaychallenge.coredata.di.DataModule
import com.ottoboni.takeawaychallenge.coredomain.di.DomainModule
import com.ottoboni.takeawaychallenge.corelocalstorage.di.LocalStorageModule

object ApplicationModules {

    private val coreModules = listOf(
        LocalStorageModule.instance,
        DataModule.instance,
        DomainModule.instance
    )

    val modules = coreModules
}
