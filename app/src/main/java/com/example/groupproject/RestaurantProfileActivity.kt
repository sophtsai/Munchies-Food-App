package com.example.groupproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class RestaurantProfileActivity: AppCompatActivity() {
    private lateinit var dishName : TextView
    private lateinit var restaurantName : TextView
    private lateinit var dishRating : RatingBar
    private lateinit var mapButton : Button
    private lateinit var dishDescriptionText : TextView
    private lateinit var restaurant : Restaurant


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_profile)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        dishName = findViewById(R.id.dishNameText)
        restaurantName = findViewById(R.id.restaurantName)
        dishRating = findViewById(R.id.dishRating)
        mapButton = findViewById(R.id.mapsField)
        dishDescriptionText = findViewById(R.id.dishDescriptionText)

        restaurant = intent.getSerializableExtra(RESTAURANT_EXTRA) as Restaurant

        dishName.text = restaurant.getDishName()
        restaurantName.text = restaurant.getRestName()
        dishRating.rating = restaurant.getAvgRating()
        dishDescriptionText.text = restaurant.getDescription()

        val handler = ButtonHandler()
        mapButton.setOnClickListener(handler)

    }

    inner class ButtonHandler : View.OnClickListener {
        override fun onClick(v: View?) {
            var intent : Intent = Intent(this@RestaurantProfileActivity,
                MapsActivity::class.java)
            intent.putExtra("RESTAURANT", restaurant)
            this@RestaurantProfileActivity.startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}