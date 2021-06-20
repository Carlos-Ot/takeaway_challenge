package com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.featurerestaurant.databinding.ItemRestaurantBinding
import com.ottoboni.takeawaychallenge.shared.extensions.get
import com.ottoboni.takeawaychallenge.shared.extensions.layoutInflater
import org.koin.core.parameter.parametersOf

class RestaurantListAdapter(private val lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    var items: List<Restaurant> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        binding = ItemRestaurantBinding.inflate(parent.layoutInflater, parent, false),
        lifecycleOwner = lifecycleOwner
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    class ViewHolder(
        private val binding: ItemRestaurantBinding,
        lifecycleOwner: LifecycleOwner,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.lifecycleOwner = lifecycleOwner
        }

        fun bind(restaurant: Restaurant) {
            get<RestaurantItemViewModel> { parametersOf(restaurant) }
                .also { viewModel ->
                    binding.viewModel = viewModel
                }

            binding.executePendingBindings()
        }
    }
}