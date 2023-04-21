package com.example.groupproject

class Food {
    private var foodTypeName : String
    private var description : String
    private var restaurants : Array<Restaurant>

    //constructor with one restaurant
    constructor (typeName : String, desc : String, restaurant : Restaurant) {
        foodTypeName = typeName
        description = desc
        restaurants = Array(1){restaurant}
    }

    //constructor with an array of restaurants
    constructor (typeName : String, desc : String, newRestaurants : Array<Restaurant>) {
        foodTypeName = typeName
        description = desc
        restaurants = newRestaurants
    }


    //getters
    fun getFoodTypeName () : String {
        return foodTypeName
    }

    fun getDescription () : String {
        return description
    }

    //returns array of restaurants
    fun getRestaurants () : Array<Restaurant> {
        return restaurants
    }

    //retrieve a restaurant at an index
    fun getRestaurantAt (index : Int) : Restaurant {
        return restaurants[index]
    }

    //get number of restaurants
    fun getNumRestaurants () : Int {
        return restaurants.size
    }


    //setters
    fun setFoodTypeName (newFoodTypeName : String) {
        foodTypeName = newFoodTypeName
    }

    fun setDescription (newDesc : String) {
        description = newDesc
    }

    //set restaurants to a new array of restaurants
    fun setRestaurants (newRestaurants : Array<Restaurant>) {
        restaurants = newRestaurants
    }

    //append one restaurant
    fun appendRestaurant (newRestaurant : Restaurant) {
        restaurants += newRestaurant
    }

    //append an array of restaurants
    fun appendRestaurants (newRestaurants : Array<Restaurant>) {
        restaurants += newRestaurants
    }

    //insert a restaurant at an index
    fun insertRestaurantAt (index : Int, newRestaurant : Restaurant) {
        restaurants = restaurants.copyOfRange(0, index) + newRestaurant +
                restaurants.copyOfRange(index, restaurants.size)
    }

    //deletes a restaurant at an index
    fun deleteRestaurantAt (index : Int) {
        restaurants = restaurants.copyOfRange(0, index) +
                restaurants.copyOfRange(index + 1, restaurants.size)
    }

    //finds out the index of the restaurant by name. Returns the moment it is found. Returns -1
    //if the restaurant is not found. If two restaurants have the same name, the latter one is
    //returned
    fun findRestaurantIndex (restaurantName : String) : Int {
        var answer = -1
        var index = 0
        for (restaurant in restaurants) {
            if (restaurant.getRestName() == restaurantName){
                answer = index
            }
            index += 1
        }
        return answer
    }

}