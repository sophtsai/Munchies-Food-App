package com.example.groupproject

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.groupproject.databinding.ActivityDisplayMapBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityDisplayMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDisplayMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        var mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //geocoding
        var address : String = "University of Maryland, College Park, MD"
        //retrieve latitude and longitude
        var geocoder : Geocoder = Geocoder (this)
        var handler : GeocodingHandler = GeocodingHandler()
        geocoder.getFromLocationName(address, 5, handler)
        var location = LatLng(latitude, longitude)
        var update: CameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 15.0f)
        var marker : MarkerOptions = MarkerOptions()
        marker.position (location)
        this@MapsActivity.mMap.moveCamera(update)
        this@MapsActivity.mMap.addMarker(marker)
    }

    inner class GeocodingHandler : Geocoder.GeocodeListener {
        override fun onGeocode(addresses: MutableList<Address>) {
            if (addresses != null) {
                var latitude = addresses.get(0).latitude
                var longitude = addresses.get(0).longitude
                Log.w("MapsActivity", "Longitude and Latitude: " + latitude + " " + longitude)



            } else {
                Log.w("MainActivity", "Sorry, no results")
            }
        }

    }

}