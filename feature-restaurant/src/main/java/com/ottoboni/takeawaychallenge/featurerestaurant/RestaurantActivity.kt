package com.ottoboni.takeawaychallenge.featurerestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ottoboni.takeawaychallenge.featurerestaurant.databinding.ActivityRestaurantBinding

class RestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil
            .setContentView<ActivityRestaurantBinding>(this, R.layout.activity_restaurant)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, RestaurantActivity::class.java)
    }
}