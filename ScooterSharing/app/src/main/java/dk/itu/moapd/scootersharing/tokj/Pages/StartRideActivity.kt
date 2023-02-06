package dk.itu.moapd.scootersharing.tokj.Pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.WindowCompat
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.tokj.Model.Scooter
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityStartRideBinding
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityUpdateRideBinding

class StartRideActivity : AppCompatActivity() {

    companion object {
        private val TAG = StartRideActivity::class.qualifiedName
    }

    private lateinit var binding: ActivityStartRideBinding

    private val scooter = Scooter("","", Long.MIN_VALUE)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window,false )
        super.onCreate(savedInstanceState)
        binding = ActivityStartRideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scooterIDText = binding.editScooterID
        val locationText = binding.editLocationText
        val letsRideButton = binding.letsRideButton

        letsRideButton.setOnClickListener {
            if (scooterIDText.text.isNotEmpty() &&
                locationText.text.isNotEmpty()){
                val name = scooterIDText.text.toString().trim();
                val location =  locationText.text.toString().trim()
                scooter.name = name
                scooter.location = location
                scooter.timestamp = System.currentTimeMillis()
                locationText.text.clear()
                scooterIDText.text.clear()
                showScooterSnackBar(binding.root,scooter,letsRideButton)
            }
        }
    }

    /**
     * @return Logs the scooter to the console
     */
    fun showScooterSnackBar(context: View, scooter: Scooter, anchor: View){
        Snackbar.make(context, scooter.toString(), Snackbar.LENGTH_SHORT).setAnchorView(anchor).show()
    }
    fun showScooterLog() {
        Log.d(StartRideActivity.TAG, scooter.toString())
    }
}