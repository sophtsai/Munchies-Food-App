package com.example.groupproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


const val FOOD_IND = "FOOD_IND"

class FoodlistRecyclerViewAdapter(private val context: Context, private val foods: Array<Food>) :
    RecyclerView.Adapter<FoodlistRecyclerViewAdapter.FoodlistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodlistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_food_card, parent, false)
        return FoodlistViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodlistViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    inner class FoodlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private var foodName: TextView = itemView.findViewById<TextView>(R.id.foodName)

        fun bind(food: Food) {
            foodName.text = food.getFoodTypeName()
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val food = foods[absoluteAdapterPosition] // Get selected food type

            val activity = context as Activity
            // Navigate to specific dish screen and pass the food type
            val intent = Intent(context, SpecificDishActivity::class.java)
            intent.putExtra(FOOD_IND, absoluteAdapterPosition)
            activity.startActivity(intent)
            activity.overridePendingTransition( R.anim.fade_in, 0 )
        }
    }

}