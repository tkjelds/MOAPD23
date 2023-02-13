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
package dk.itu.moapd.scootersharing.tokj.Pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.tokj.Model.RidesDB
import dk.itu.moapd.scootersharing.tokj.Model.Scooter
import dk.itu.moapd.scootersharing.tokj.R
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityMainBinding
import java.util.jar.Attributes.Name

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var ridesDB: RidesDB
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        ridesDB = RidesDB.get(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val startRideButton = binding.startRideButton
        val updateRideButton = binding.updateRideButton
        val scooterList = binding.scooterRecyclerView
        scooterList.adapter = ScooterAdapter(ridesDB.getRidesList())
        startRideButton.setOnClickListener {
            val intent = Intent(this, StartRideActivity::class.java)
            startActivity(intent)
        }
        updateRideButton.setOnClickListener {
            val intent = Intent(this, UpdateRideActivity::class.java)
            startActivity(intent)
        }
    }
}

class ScooterAdapter(private val scooterDB: List<Scooter>) :
    RecyclerView.Adapter<ScooterAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val NametextView: TextView
        val LocationtextView: TextView
        val TimeStamptextView: TextView
        lateinit var scooter: Scooter

        init {
            // Define click listener for the ViewHolder's View
            NametextView = view.findViewById(R.id.scooterName)
            LocationtextView = view.findViewById(R.id.location)
            TimeStamptextView = view.findViewById(R.id.timeStamp)
            NametextView.setOnClickListener {
                Snackbar.make(view, scooter.toString(), Snackbar.LENGTH_SHORT)
                    .setAnchorView(NametextView).show()
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_rides, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val scooter = scooterDB.get(position)
        viewHolder.scooter = scooter
        viewHolder.NametextView.text = scooter.name
        viewHolder.LocationtextView.text = scooter.location
        viewHolder.TimeStamptextView.text = scooter.convertLongToTime()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = scooterDB.size
}