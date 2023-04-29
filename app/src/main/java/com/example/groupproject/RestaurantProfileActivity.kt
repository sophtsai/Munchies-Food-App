package com.example.groupproject

import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RestaurantProfileActivity: AppCompatActivity() {
    private lateinit var dishName : TextView
    private lateinit var restaurantName : TextView
    private lateinit var dishRating : RatingBar
    private lateinit var dishDescriptionText : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_profile)

        dishName = findViewById(R.id.dishNameText)
        restaurantName = findViewById(R.id.restaurantName)
        dishRating = findViewById(R.id.dishRating)
        dishDescriptionText = findViewById(R.id.dishDescriptionText)

        val restaurant : Restaurant = intent.getSerializableExtra(RESTAURANT_EXTRA) as Restaurant

        dishName.text = restaurant.getDishName()
        restaurantName.text = restaurant.getRestName()
        dishRating.rating = restaurant.getAvgRating()
        dishDescriptionText.text = restaurant.getDescription()

    }
}