/**
 * MIT License

Copyright (c) 2023 Tore Asbjørn Tonnisen Kjelds

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package dk.itu.moapd.scootersharing.tokj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.view.WindowCompat
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityMainBinding
import java.security.cert.TrustAnchor

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.qualifiedName
    }
    private lateinit var binding : ActivityMainBinding
    private val scooter = Scooter("","")

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows ( window , false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
                locationText.text.clear()
                scooterIDText.text.clear()
                showScooterSnackBar(binding.root,scooter,letsRideButton)
            }
        }
    }

    /**
     * @return Logs the scooter to the console
     */
    fun showScooterSnackBar(context: View, scooter: Scooter,anchor: View){
        Snackbar.make(context, scooter.toString(), Snackbar.LENGTH_SHORT).setAnchorView(anchor).show()
    }
    fun showScooterLog() {
        Log.d(TAG, scooter.toString())
    }
}