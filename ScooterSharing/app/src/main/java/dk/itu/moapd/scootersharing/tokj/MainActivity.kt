package dk.itu.moapd.scootersharing.tokj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.qualifiedName
    }
    private lateinit var scooterIDText :EditText
    private lateinit var locationText : EditText
    private lateinit var letsRideButton : Button
    private val scooter = Scooter("","")
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows ( window , false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scooterIDText = findViewById(R.id.edit_scooter_ID)
        locationText = findViewById(R.id.edit_location_text)
        letsRideButton = findViewById(R.id.lets_ride_button)

        letsRideButton.setOnClickListener {
            if (scooterIDText.text.isNotEmpty() &&
                    locationText.text.isNotEmpty()){
                val name = scooterIDText.text.toString().trim();
                val location =  locationText.text.toString().trim()
                scooter.name = name
                scooter.location = location
                locationText.text.clear()
                scooterIDText.text.clear()
                showScooterLog()
            }
        }
    }
    fun showScooterLog() {
        Log.d(TAG, scooter.toString())
    }
}