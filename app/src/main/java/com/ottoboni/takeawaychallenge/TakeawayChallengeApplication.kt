package com.ottoboni.takeawaychallenge

import android.app.Application
import com.ottoboni.takeawaychallenge.di.ApplicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TakeawayChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() = startKoin {
        androidLogger(Level.DEBUG)
        androidContext(this@TakeawayChallengeApplication)
        modules(ApplicationModules.modules)
    }
}
