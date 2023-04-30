package com.example.groupproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val RESTAURANT_EXTRA = "RESTAURANT_EXTRA"

class RestaurantRecyclerViewAdapter(private val context: Context,
                                    private val restaurants: Array<Restaurant>) :
    RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_restaurant_card, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private var restaurantText: TextView = itemView.findViewById<TextView>(R.id.restaurantText)

        fun bind(restaurant: Restaurant) {
            restaurantText.text = restaurant.getAvgRating().toString() + " - " + restaurant.getRestName()
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val restaurant = restaurants[absoluteAdapterPosition] // Get selected food type

            val activity = context as Activity
            // Navigate to specific dish screen and pass the food type
            val intent = Intent(context, RestaurantProfileActivity::class.java)
            intent.putExtra(RESTAURANT_EXTRA, restaurant)
            activity.startActivity(intent)
            activity.overridePendingTransition( R.anim.fade_in, 0 )
        }
    }

}