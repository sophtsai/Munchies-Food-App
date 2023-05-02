package com.example.groupproject


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.content.Intent
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class MainActivity : AppCompatActivity() {
    private lateinit var foodListRv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTestData()

        foodListRv = findViewById(R.id.foodlistRv)

         var listOfFoods = foodList.getFoodArray() // Get list of food types

        // Create adapter passing in list of food types
        val adapter = FoodlistRecyclerViewAdapter(this, listOfFoods)

        foodListRv.adapter = adapter // Attach adapter to recycler view to populate items
        foodListRv.layoutManager = LinearLayoutManager(this) // Set layout manager to position items

        // Build the AdView
        val adView = AdView(this)
        val adSize = AdSize(AdSize.FULL_WIDTH, AdSize.AUTO_HEIGHT)
        adView.setAdSize(adSize)
        val adUnitId = "ca-app-pub-3940256099942544/6300978111"
        adView.adUnitId = adUnitId

        // Build the AdRequest
        val builder : AdRequest.Builder = AdRequest.Builder()
        builder.addKeyword("restaurant")
        builder.addKeyword("food")
        val request : AdRequest = builder.build()

        // Add adView to LinearLayout
        val adLayout : LinearLayout = findViewById( R.id.ad_view )
        adLayout.addView(adView)

        // Load the ad
        try {
            adView.loadAd(request)
        } catch (e : Exception) {
            Log.w("MainActivity", "Ad failed to load")
        }

        // If the x button is clicked, make the ad and button disappear
        val closeButton = findViewById<Button>(R.id.close_button)
        closeButton.setOnClickListener {
            adLayout.visibility = View.GONE
            closeButton.visibility = View.GONE
        }
    }

    private fun setTestData ( ) {
        /** Restaurants  - create restaurants using sample data **/
        val saladworks = Restaurant("Saladworks", "Chicken Noodle Soup",
            "Saladworks' chicken noodle soup is known for its fresh and healthy ingredients, " +
                    "such as tender pieces of antibiotic-free chicken and farm-fresh vegetables, " +
                    "that are carefully selected and combined with a flavorful broth, making it a " +
                    "nutritious and satisfying option among other fast-casual restaurants.",
            "214 Campus Drive, College Park, MD 20742", 2.5f)
        val umdSouthSoup = Restaurant("University of Maryland South Campus Dining Hall",
        "Hearty Chicken Noodle Soup", "UMD South Dining Hall's chicken noodle soup is a " +
                    "comforting and classic dish that features tender pieces of chicken, hearty egg " +
                    "noodles, and a savory broth infused with aromatic herbs and spices, providing " +
                    "a warm and satisfying meal option for students and faculty alike.",
        "7093 Preinkert Dr, College Park, MD 20740", 4.0f)
        val hanami = Restaurant("Hanami", "Chicken Teriyaki",
        "Hanami's chicken teriyaki is known for its perfectly grilled and juicy chicken, " +
                "combined with a well-balanced and flavorful teriyaki glaze, and served with " +
                "high-quality, freshly prepared sides that elevate the dish and make it stand out " +
                "among other Japanese restaurants.",
            "8145 Baltimore Ave Ste M, College Park, MD 20740-2471", 5.0f)
        val umdSouthTeriyaki = Restaurant("University of Maryland South Campus Dining Hall",
        "Teriyaki Chicken", "UMD South Dining Hall's teriyaki chicken is typically " +
                    "a delicious dish featuring tender pieces of grilled or broiled chicken coated " +
                    "in a flavorful teriyaki sauce, served with a side of steamed rice and " +
                    "vegetables, providing a tasty and convenient meal option for students and " +
                    "faculty on campus.", "7093 Preinkert Dr, College Park, MD 20740", 1.5f)
        val pupuseria = Restaurant("Pupuseria La Familiar", "Pupusas",
        "Pupuseria La Familiar's pupusas are known for their authentic Salvadoran flavors " +
                "and generous fillings, along with the skillful grilling techniques that result " +
                "in a crispy exterior and a warm, gooey interior, making them a standout among " +
                "other pupuserias.",
        "8145 Baltimore Ave Ste K, College Park, MD 20740-2491", 1.0f)

        /** Foods - create all the foods using above restaurants **/
        val chickenNoodle = Food("Chicken Noodle Soup", "The heartwarming " +
                "chicken noodle soup is a savory and comforting dish featuring succulent chunks " +
                "of chicken, aromatic herbs and spices, and silky egg noodles, all simmered " +
                "together in a flavorful broth that will warm your soul and leave you feeling " +
                "fully satisfied.", arrayOf(umdSouthSoup, saladworks)
        )
        val teriyaki = Food("Teriyaki Chicken", "The mouthwatering chicken " +
                "teriyaki features tender, juicy chicken smothered in a deliciously sweet and " +
                "savory teriyaki glaze, served with a side of fluffy rice and crisp stir-fried " +
                "vegetables that add texture and freshness to every bite.", arrayOf(hanami, umdSouthTeriyaki))
        val pupusa = Food("Pupusa", "Pupusas are a mouthwatering Salvadoran " +
                "treat that features thick handmade corn tortillas stuffed with a variety of " +
                "flavorful fillings, grilled to perfection, and served with a tangy slaw and " +
                "spicy tomato salsa that add the perfect touch of freshness and heat to every " +
                "bite.", pupuseria)

        /** Foodlist - set testData **/
        foodList = Foodlist(arrayOf(chickenNoodle, teriyaki, pupusa))
    }

    companion object {
        lateinit var foodList : Foodlist
    }
}