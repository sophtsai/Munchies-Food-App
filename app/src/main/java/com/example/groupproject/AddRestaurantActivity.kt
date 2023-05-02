package com.example.groupproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddRestaurantActivity : AppCompatActivity() {
    private lateinit var restaurantListRv : RecyclerView
    private lateinit var dishNameET : EditText
    private lateinit var restaurantNameET : EditText
    private lateinit var dishRating : RatingBar
    private lateinit var mapAddressET : EditText
    private lateinit var dishDescriptionET : EditText
    private lateinit var saveButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_restaurant)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        dishNameET = findViewById(R.id.dishNameInput)
        restaurantNameET = findViewById(R.id.restaurantNameInput)
        dishRating = findViewById(R.id.dishRating)
        mapAddressET = findViewById(R.id.mapsFieldInput)
        dishDescriptionET = findViewById(R.id.dishDescriptionInput)
        saveButton = findViewById(R.id.saveButton)

        restaurantListRv = SpecificDishActivity.restaurantListRv

    }

    fun saveDish(v: View) {
        addRestaurantDish()

        // go back
        finish( )
        overridePendingTransition( R.anim.fade_out, 0 )
    }

    // TODO: save form data to persistent data

    private fun addRestaurantDish() {
        var dishNameString : String = dishNameET.text.toString()
        var restaurantNameString : String = restaurantNameET.text.toString()
        var dishRatingFloat : Float = dishRating.rating
        var mapAddressString : String = mapAddressET.text.toString()
        var dishDescriptionString : String = dishDescriptionET.text.toString()

        //creating a new restaurant and attaching it to data structure
        var newRestaurant : Restaurant = Restaurant(restaurantNameString, dishNameString,
            dishDescriptionString, mapAddressString, dishRatingFloat)

        //attach it to the food that user clicked
        val foodType : Food? = SpecificDishActivity.foodType
        foodType!!.appendRestaurant(newRestaurant)

        //getting the adapter to work
        val location : Int = foodType.getNumRestaurants() - 1
        restaurantListRv.adapter?.notifyItemInserted(location)

        val adapter = RestaurantRecyclerViewAdapter(this, foodType.getRestaurants())

        restaurantListRv.adapter = adapter
        restaurantListRv.adapter?.notifyItemInserted(location)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                overridePendingTransition( 0, R.anim.fade_out )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}