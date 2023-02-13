package dk.itu.moapd.scootersharing.tokj.Pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.text.set
import androidx.core.view.WindowCompat
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.tokj.Model.RidesDB
import dk.itu.moapd.scootersharing.tokj.Model.Scooter
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityUpdateRideBinding

class UpdateRideActivity : AppCompatActivity() {

    companion object {
        lateinit var ridesDB: RidesDB
    }

    private lateinit var binding: ActivityUpdateRideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        ridesDB = RidesDB.get(this)
        binding = ActivityUpdateRideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var scooterIDText = binding.editScooterID
        val locationText = binding.editLocationText
        val updateRideButton = binding.letsRideButton
        scooterIDText.setText(ridesDB.getCurrentScooter().name.trim())

        updateRideButton.setOnClickListener {
            if (scooterIDText.text.isNotEmpty() &&
                locationText.text.isNotEmpty()
            ) {
                val location = locationText.text.toString().trim()
                ridesDB.updateCurrentScooter(location)
                showScooterSnackBar(binding.root, ridesDB.getCurrentScooter(), updateRideButton)
                scooterIDText.text.clear()
            }
        }
    }


    /**
     * @return Logs the scooter to the console
     */
    fun showScooterSnackBar(context: View, scooter: Scooter, anchor: View) {
        Snackbar.make(context, scooter.toString(), Snackbar.LENGTH_SHORT).setAnchorView(anchor)
            .show()
    }
}