package com.ottoboni.takeawaychallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ottoboni.takeawaychallenge.featurerestaurant.RestaurantActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(RestaurantActivity.newIntent(this))
        finish()
    }
}
