package com.ottoboni.takeawaychallenge.featurerestaurant.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ottoboni.takeawaychallenge.featurerestaurant.databinding.FragmentRestaurantDetailsBinding
import com.ottoboni.takeawaychallenge.featurerestaurant.details.di.RestaurantDetailsModule
import com.ottoboni.takeawaychallenge.shared.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf

class RestaurantDetailsFragment : BaseFragment() {

    override val module: Module
        get() = RestaurantDetailsModule.instance

    private lateinit var binding: FragmentRestaurantDetailsBinding

    private val arguments: RestaurantDetailsFragmentArgs by navArgs()

    private val viewModel: RestaurantDetailsViewModel by viewModel {
        parametersOf(arguments.restaurant)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentRestaurantDetailsBinding
        .inflate(inflater)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }
}
