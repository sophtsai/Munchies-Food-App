package com.example.groupproject

import android.util.Log
import java.io.Serializable

class Restaurant : Serializable {
    private var restName : String
    private var dishName : String
    private var description : String
    private var address : String
    private var avgRating : Float
    private var latitude : Double
    private var longitude : Double

    //constructor with default rating set to -1.0f if the user does not enter a rating
    constructor (newRestName : String, newDishName : String, desc : String,
                 newAddress : String, rating : Float = -1.0f) {
        restName = newRestName
        dishName = newDishName
        avgRating = rating
        description = desc
        address = newAddress
        latitude = 0.0
        longitude = 0.0
    }

    //getters
    fun getRestName () : String {
        return restName
    }

    fun getDishName () : String {
        return dishName
    }

    fun getDescription () : String {
        return description
    }

    fun getAddress () : String {
        return address
    }

    fun getAvgRating () : Float {
        return avgRating
    }

    fun getLatitude () : Double {
        return latitude
    }

    fun getLongitude() : Double {
        return longitude
    }

    //setters
    fun setRestName (newRestName : String) {
        restName = newRestName
    }

    fun setDishName (newDishName : String) {
        dishName = newDishName
    }

    fun setDescription (newDescription : String) {
        description = newDescription
    }

    fun setAddress (newAddress : String) {
        address = newAddress
    }

    fun setAvgRating (rating : Float) {
        avgRating = rating
    }

    fun setLatitude (newLat : Double) {
        latitude = newLat
        Log.w("MapsActivity", "Set Latitude: " + latitude)
    }

    fun setLongitude (newLong : Double) {
        longitude = newLong
        Log.w("MapsActivity", "Set Longitude: " + longitude)
    }
}