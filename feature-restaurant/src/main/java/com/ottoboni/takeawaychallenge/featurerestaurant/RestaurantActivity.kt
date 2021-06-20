package com.ottoboni.takeawaychallenge.featurerestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.ottoboni.takeawaychallenge.featurerestaurant.databinding.ActivityRestaurantBinding

class RestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil
            .setContentView<ActivityRestaurantBinding>(this, R.layout.activity_restaurant)
    }

    override fun onBackPressed() {
        if (!findNavController(R.id.fcv_restaurant).popBackStack())
            finish()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, RestaurantActivity::class.java)
    }
}