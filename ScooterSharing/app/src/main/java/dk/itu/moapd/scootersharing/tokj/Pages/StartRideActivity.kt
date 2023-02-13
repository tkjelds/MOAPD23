package dk.itu.moapd.scootersharing.tokj.Pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.WindowCompat
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.tokj.Model.RidesDB
import dk.itu.moapd.scootersharing.tokj.Model.Scooter
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityStartRideBinding
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityUpdateRideBinding

class StartRideActivity : AppCompatActivity() {

    companion object {
        private val TAG = StartRideActivity::class.qualifiedName
        lateinit var ridesDB: RidesDB
    }

    private lateinit var binding: ActivityStartRideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        ridesDB = RidesDB.get(this)
        binding = ActivityStartRideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scooterIDText = binding.editScooterID
        val locationText = binding.editLocationText
        val letsRideButton = binding.letsRideButton

        letsRideButton.setOnClickListener {
            if (scooterIDText.text.isNotEmpty() &&
                locationText.text.isNotEmpty()
            ) {
                val name = scooterIDText.text.toString().trim()
                val location = locationText.text.toString().trim()
                var foundBike =
                    ridesDB.getRidesList().find { it.name == name && it.location == location }
                if (foundBike == null) showScooterSnackBar(
                    binding.root,
                    letsRideButton,
                    "Ride not found",
                )
                else {
                    ridesDB.setCurrentScooter(foundBike)
                    showScooterSnackBar(
                        binding.root,
                        letsRideButton,
                        "CurrentScooter Set as $foundBike"
                    )
                }
                locationText.text.clear()
                scooterIDText.text.clear()
            }
        }
    }

    /**
     * @return Logs the scooter to the console
     */
    fun showScooterSnackBar(context: View, anchor: View, text: String) {
        Snackbar.make(context, text, Snackbar.LENGTH_SHORT).setAnchorView(anchor)
            .show()
    }
}