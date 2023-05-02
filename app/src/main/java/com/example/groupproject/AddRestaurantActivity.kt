package com.example.groupproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
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

        //persistent data handling
        var tch : TextChangeHandler = TextChangeHandler()
        var rbh : RatingHandler = RatingHandler()


        dishNameET.addTextChangedListener(tch)
        restaurantNameET.addTextChangedListener(tch)
        dishRating.onRatingBarChangeListener = rbh
        mapAddressET.addTextChangedListener(tch)
        dishDescriptionET.addTextChangedListener(tch)

        //persistent data
        var context = this@AddRestaurantActivity
        var pref : SharedPreferences = context.getSharedPreferences(context.packageName+ "_preferences",
            Context.MODE_PRIVATE)
        dishNameET.setText(pref.getString(DISH_NAME, ""))
        restaurantNameET.setText(pref.getString(REST_NAME, ""))
        dishRating.setRating(pref.getFloat(RATING, 0.0f))
        mapAddressET.setText(pref.getString(ADDR, ""))
        dishDescriptionET.setText(pref.getString(DISH_DESC, ""))

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

        setPreferences(this@AddRestaurantActivity, "", "",
            0.0f, "", "")

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

    inner class RatingHandler : RatingBar.OnRatingBarChangeListener {
        override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
            var dishNameString : String = dishNameET.text.toString()
            var restaurantNameString : String = restaurantNameET.text.toString()
            var dishRatingFloat : Float = dishRating.rating
            var mapAddressString : String = mapAddressET.text.toString()
            var dishDescriptionString : String = dishDescriptionET.text.toString()
            setPreferences(this@AddRestaurantActivity, dishNameString, restaurantNameString,
                dishRatingFloat, mapAddressString, dishDescriptionString)
        }

    }

    inner class TextChangeHandler : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            var dishNameString : String = dishNameET.text.toString()
            var restaurantNameString : String = restaurantNameET.text.toString()
            var dishRatingFloat : Float = dishRating.rating
            var mapAddressString : String = mapAddressET.text.toString()
            var dishDescriptionString : String = dishDescriptionET.text.toString()
            setPreferences(this@AddRestaurantActivity, dishNameString, restaurantNameString,
                dishRatingFloat, mapAddressString, dishDescriptionString)
        }

        override fun afterTextChanged(s: Editable?) {
            var dishNameString : String = dishNameET.text.toString()
            var restaurantNameString : String = restaurantNameET.text.toString()
            var dishRatingFloat : Float = dishRating.rating
            var mapAddressString : String = mapAddressET.text.toString()
            var dishDescriptionString : String = dishDescriptionET.text.toString()
            setPreferences(this@AddRestaurantActivity, dishNameString, restaurantNameString,
                dishRatingFloat, mapAddressString, dishDescriptionString)
        }

    }

    fun setPreferences(context: Context, dishName : String, restName : String, rate : Float, addr : String, desc : String) {
        var pref : SharedPreferences = context.getSharedPreferences(context.packageName+ "_preferences",
            Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = pref.edit()
        editor.putString(DISH_NAME, dishName)
        editor.putString(REST_NAME, restName)
        editor.putFloat(RATING, rate)
        editor.putString(ADDR, addr)
        editor.putString(DISH_DESC, desc)
        editor.commit()
    }

    companion object {
        const val DISH_NAME : String = "DISH_NAME"
        const val REST_NAME : String = "REST_NAME"
        const val RATING : String = "RATING"
        const val ADDR : String = "ADDR"
        const val DISH_DESC : String = "DISH_DESC"
    }
}