package com.ottoboni.takeawaychallenge.shared.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseActivity : AppCompatActivity() {
    protected abstract val module: Module

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(module)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(module)
    }
}
