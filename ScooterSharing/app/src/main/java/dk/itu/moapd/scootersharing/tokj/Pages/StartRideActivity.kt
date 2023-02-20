package dk.itu.moapd.scootersharing.tokj.Pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.WindowCompat
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.tokj.Model.RidesDB
import dk.itu.moapd.scootersharing.tokj.Model.Scooter
import dk.itu.moapd.scootersharing.tokj.R
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityStartRideBinding
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityUpdateRideBinding

class StartRideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_ride)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.StartRideActivityFragmentContainer)

        if (currentFragment == null) {
            val fragment = StartRideFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.StartRideActivityFragmentContainer, fragment).commit()
        }

    }
}