package com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ottoboni.takeawaychallenge.featurerestaurant.databinding.FragmentRestaurantListBinding
import com.ottoboni.takeawaychallenge.featurerestaurant.list.di.RestaurantListModule
import com.ottoboni.takeawaychallenge.shared.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.module.Module

class RestaurantListFragment : BaseFragment() {

    override val module: Module
        get() = RestaurantListModule.instance

    private lateinit var binding: FragmentRestaurantListBinding

    private val viewModel: RestaurantListViewModel by viewModel()

    private lateinit var adapter: RestaurantListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = FragmentRestaurantListBinding
        .inflate(inflater)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setupViews()
        observeEvents()
    }

    private fun setupViews() = with(binding) {
        adapter = RestaurantListAdapter(viewLifecycleOwner)
        rvRestaurantList.adapter = adapter
    }

    private fun observeEvents() = with(viewModel) {
        restaurants.observe(viewLifecycleOwner) {
            adapter.items = it
        }
    }
}
