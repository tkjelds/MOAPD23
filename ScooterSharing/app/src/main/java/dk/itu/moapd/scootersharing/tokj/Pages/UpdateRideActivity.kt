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
import dk.itu.moapd.scootersharing.tokj.R
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityUpdateRideBinding

class UpdateRideActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_ride)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.UpdateRideActivityFragmentContainer)

        if (currentFragment == null) {
            val fragment = UpdateRideFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.UpdateRideActivityFragmentContainer, fragment).commit()
        }

    }
}