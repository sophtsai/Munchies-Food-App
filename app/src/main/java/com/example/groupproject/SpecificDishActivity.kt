package com.example.groupproject

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable


class SpecificDishActivity: AppCompatActivity() {
    private lateinit var foodTypeTitle : TextView
    private lateinit var foodTypeDesc : TextView
    private lateinit var restaurantList : Array<Restaurant>
    private lateinit var addRestaurantFAB : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_dish)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        var index = intent.getIntExtra(FOOD_IND, 0)
        foodType = MainActivity.foodList.getFoodArray()[index]

        foodTypeTitle = findViewById(R.id.specificFoodTitle)
        foodTypeDesc = findViewById(R.id.dishDescriptionText)
        restaurantListRv = findViewById(R.id.specificFoodRv)
        addRestaurantFAB = findViewById(R.id.addRestaurantButton)

        // Set corresponding fields on page
        foodTypeTitle.text = foodType.getFoodTypeName()
        foodTypeDesc.text = foodType.getDescription()
        restaurantList = foodType.getRestaurants() // Get list of restaurants

        // Create adapter passing in list of restaurants
        val adapter = RestaurantRecyclerViewAdapter(this, restaurantList)

        restaurantListRv.adapter = adapter // Attach adapter to recycler view to populate items
        restaurantListRv.layoutManager = LinearLayoutManager(this) // Set layout manager to position items

        addRestaurantFAB.setOnClickListener {
            val intent = Intent(this, AddRestaurantActivity::class.java)
            startActivity(intent)
            overridePendingTransition( R.anim.fade_in, 0 )
        }
    }


    private fun <T : Serializable?> getSerializable(intent: Intent, key: String, m_class: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(key, m_class)!!
        else
            intent.getSerializableExtra(key) as T
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition( 0, R.anim.fade_out )
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    companion object {
        lateinit var foodType : Food
        lateinit var restaurantListRv : RecyclerView
    }



}