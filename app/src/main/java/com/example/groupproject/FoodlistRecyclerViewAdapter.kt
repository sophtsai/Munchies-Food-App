package com.example.groupproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FoodlistRecyclerViewAdapter(private val foods: Array<Food>) :
    RecyclerView.Adapter<FoodlistRecyclerViewAdapter.FoodlistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodlistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_food_card, parent, false)
        return FoodlistViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodlistViewHolder, position: Int) {
        val food = foods[position]

        holder.foodName.text = food.getFoodTypeName()
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    inner class FoodlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foodName: TextView = itemView.findViewById<TextView>(R.id.foodName)
    }

}