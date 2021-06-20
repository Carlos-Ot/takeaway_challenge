package com.ottoboni.takeawaychallenge.featurerestaurant.bindings

import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus.CLOSED
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus.OPEN
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus.ORDER_AHEAD
import com.ottoboni.takeawaychallenge.featurerestaurant.R

@BindingAdapter("openingStatus")
fun TextView.setOpeningStatus(openingStatus: OpeningStatus?) {
    when (openingStatus) {
        OPEN -> {
            setText(R.string.restaurant_opening_status_open)
            setTextColor(context.getColor(R.color.medium_forest_green))
        }
        ORDER_AHEAD -> {
            setText(R.string.restaurant_opening_status_order_ahead)
            setTextColor(context.getColor(R.color.cantaloupe))
        }
        CLOSED -> {
            setText(R.string.restaurant_opening_status_closed)
            setTextColor(context.getColor(R.color.chili_pepper))
        }
        else -> {
            setText(R.string.restaurant_opening_status_closed)
            setTextColor(context.getColor(R.color.chili_pepper))
        }
    }
}

@BindingAdapter("toggleStatus")
fun ImageButton.setupAsToggleButton(toggleStatus: Boolean) {
    isActivated = toggleStatus
}
