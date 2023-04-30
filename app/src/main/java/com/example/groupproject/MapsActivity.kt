package com.example.groupproject

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.groupproject.databinding.ActivityDisplayMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapFragment : SupportMapFragment
    private lateinit var restaurant : Restaurant
    private lateinit var mMap : GoogleMap
    private lateinit var binding : ActivityDisplayMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding = ActivityDisplayMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //retrieving from intent
        restaurant = intent.getSerializableExtra("RESTAURANT") as Restaurant
        val address = restaurant.getAddress()

        //geocoding
        if (address != null) {
            //retrieve latitude and longitude
            var geocoder : Geocoder = Geocoder (this)
            var addresses = geocoder.getFromLocationName(address, 5)
            if (addresses != null && addresses.isNotEmpty()) {
                var latitude = addresses.get(0).latitude
                var longitude = addresses.get(0).longitude
                restaurant.setLatitude(latitude)
                restaurant.setLongitude(longitude)
                var location = LatLng(restaurant.getLatitude(), restaurant.getLongitude())

                //moving map camera to location
                try {
                    var update: CameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 17.0f)
                    mMap.moveCamera(update)
                    mMap.addMarker(MarkerOptions().position(location))
                } catch (e: Exception) {
                    Log.w("MapsActivity", "Exception: " + e.message)
                }

            } else {
                Log.w("MainActivity", "Sorry, no results")
            }


        }
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