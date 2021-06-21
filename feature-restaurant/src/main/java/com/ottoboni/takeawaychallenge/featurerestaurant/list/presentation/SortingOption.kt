package com.ottoboni.takeawaychallenge.featurerestaurant.list.presentation

enum class SortingOption {
    BEST_MATCH,
    NEWEST,
    RATING_AVERAGE,
    DISTANCE,
    POPULARITY,
    AVERAGE_PRODUCT_PRICE,
    DELIVERY_COSTS,
    MIN_COST;

    override fun toString(): String {
        return name
            .replace('_', ' ')
            .lowercase()
    }
}
