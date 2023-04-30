package com.example.groupproject

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity

class AddRestaurantActivity : AppCompatActivity() {
    private lateinit var dishNameET : EditText
    private lateinit var restaurantNameET : EditText
    private lateinit var dishRating : RatingBar
    private lateinit var mapAddressET : EditText
    private lateinit var dishDescriptionET : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_restaurant)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        dishNameET = findViewById(R.id.dishNameInput)
        restaurantNameET = findViewById(R.id.restaurantNameInput)
        dishRating = findViewById(R.id.dishRating)
        mapAddressET = findViewById(R.id.mapsFieldInput)
        dishDescriptionET = findViewById(R.id.dishDescriptionInput)
    }

    fun saveDish(v: View) {
        addRestaurantDish()

        // go back
        finish( )
        overridePendingTransition( R.anim.fade_out, 0 )
    }

    private fun addRestaurantDish() {
        var dishNameString : String = dishNameET.text.toString()
        var restaurantNameString : String = restaurantNameET.text.toString()
        var dishRatingFloat : Float = dishRating.rating
        var mapAddressString : String = mapAddressET.text.toString()
        var dishDescriptionString : String = dishDescriptionET.text.toString()

        // TODO: ADD AND SAVE NEW RESTAURANT
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                overridePendingTransition( R.anim.fade_out, 0 )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}