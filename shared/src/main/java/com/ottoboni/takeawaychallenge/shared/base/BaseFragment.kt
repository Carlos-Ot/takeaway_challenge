package com.ottoboni.takeawaychallenge.shared.base

import android.content.Context
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseFragment : Fragment() {
    protected abstract val module: Module

    override fun onAttach(context: Context) {
        loadKoinModules(module)
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        unloadKoinModules(module)
    }
}