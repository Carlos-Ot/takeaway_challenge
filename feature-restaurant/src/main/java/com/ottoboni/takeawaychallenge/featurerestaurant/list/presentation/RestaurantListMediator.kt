package com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation

import androidx.lifecycle.LiveData
import com.ottoboni.takeawaychallenge.shared.livedata.SingleLiveEvent

interface RestaurantListMediator {

    val onToggleFavoriteItem: LiveData<Unit>

    fun onToggleFavoriteItem()
}

class RestaurantListMediatorImpl : RestaurantListMediator {

    private val _onToggleFavoriteItem = SingleLiveEvent<Unit>()
    override val onToggleFavoriteItem: LiveData<Unit>
        get() = _onToggleFavoriteItem

    override fun onToggleFavoriteItem() {
        _onToggleFavoriteItem.call()
    }
}
