package com.example.groupproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpecificDishActivity: AppCompatActivity() {
    private lateinit var restaurantListRv : RecyclerView
    private lateinit var foodTypeTitle : TextView
    private lateinit var foodTypeDesc : TextView
    private lateinit var restaurantList : Array<Restaurant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_dish)

        foodTypeTitle = findViewById(R.id.specificFoodTitle)
        foodTypeDesc = findViewById(R.id.dishDescriptionText)
        restaurantListRv = findViewById(R.id.specificFoodRv)

        val foodType : Food = intent.getSerializableExtra(FOOD_EXTRA) as Food

        foodTypeTitle.text = foodType.getFoodTypeName()
        foodTypeDesc.text = foodType.getDescription()
        restaurantList = foodType.getRestaurants() // Get list of restaurants


        // Create adapter passing in list of restaurants
        val adapter = RestaurantRecyclerViewAdapter(this, restaurantList)

        restaurantListRv.adapter = adapter // Attach adapter to recycler view to populate items
        restaurantListRv.layoutManager = LinearLayoutManager(this) // Set layout manager to position items
    }
}