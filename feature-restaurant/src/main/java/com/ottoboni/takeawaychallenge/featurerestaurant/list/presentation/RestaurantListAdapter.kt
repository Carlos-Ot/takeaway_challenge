package com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.ottoboni.takeawaychallenge.coredata.domain.model.Restaurant
import com.ottoboni.takeawaychallenge.featurerestaurant.RestaurantNavGraphDirections
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

    override fun onViewRecycled(holder: ViewHolder) = holder.recycle()

    class ViewHolder(
        private val binding: ItemRestaurantBinding,
        private val lifecycleOwner: LifecycleOwner,
    ) : RecyclerView.ViewHolder(binding.root) {

        private var viewModel: RestaurantItemViewModel? = null

        init {
            binding.lifecycleOwner = lifecycleOwner
        }

        fun bind(restaurant: Restaurant) {
            viewModel = get<RestaurantItemViewModel> { parametersOf(restaurant) }
                .also { viewModel ->
                    binding.viewModel = viewModel
                    observeEvents(viewModel, restaurant)
                }

            binding.executePendingBindings()
        }

        fun recycle() {
            removeObservers(viewModel ?: return)
            viewModel = null
        }

        private fun observeEvents(viewModel: RestaurantItemViewModel, restaurant: Restaurant) {
            viewModel.onItemClicked.observe(lifecycleOwner) {
                findNavController(binding.root).navigate(
                    RestaurantNavGraphDirections.actionGlobalNavigateToRestaurantDetails(restaurant)
                )
            }
        }

        private fun removeObservers(viewModel: RestaurantItemViewModel) =
            viewModel.onItemClicked.removeObservers(lifecycleOwner)
    }
}