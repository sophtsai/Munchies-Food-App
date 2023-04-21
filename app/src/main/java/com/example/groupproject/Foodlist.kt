package com.example.groupproject

class Foodlist {
    private lateinit var foods : Array<Food>

    //constructor for single food item
    constructor (food : Food) {
        foods = Array<Food> (1) {food}
    }

    //constructor for food array being assigned
    constructor (foodArray : Array<Food>) {
        foods = foodArray
    }

    //getter
    fun getFoodArray () : Array<Food> {
        return foods
    }

    fun getNumFood () : Int {
        return foods.size
    }

    //setter
    fun setFoods (newFoods : Array<Food>) {
        foods = newFoods
    }

    fun appendFood (newFood : Food) {
        foods += newFood
    }

    fun insertFoodAt (index : Int, newFood : Food) {
        foods = foods.copyOfRange(0, index) + newFood +
                foods.copyOfRange(index, foods.size)
    }

    fun deleteFoodAt (index : Int) {
        foods = foods.copyOfRange(0, index) +
                foods.copyOfRange(index + 1, foods.size)
    }
}