package dk.itu.moapd.scootersharing.tokj.Pages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.tokj.ConfirmDeleteRideDialog
import dk.itu.moapd.scootersharing.tokj.Model.RidesDB
import dk.itu.moapd.scootersharing.tokj.Model.Scooter
import dk.itu.moapd.scootersharing.tokj.R
import dk.itu.moapd.scootersharing.tokj.databinding.FragmentMainBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private lateinit var binding: FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    companion object {
        lateinit var ridesDB: RidesDB
        lateinit var adapter: ScooterAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        ridesDB = RidesDB.get(requireActivity())
        adapter = ScooterAdapter(ridesDB)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ridesDB = RidesDB.get(requireActivity())
        binding.scooterRecyclerView.adapter = adapter
        binding.startRideButton.setOnClickListener {
            val intent = Intent(activity, StartRideActivity::class.java)
            startActivity(intent)
        }
        binding.updateRideButton.setOnClickListener {
            val intent = Intent(activity, UpdateRideActivity::class.java)
            startActivity(intent)
        }

        ItemTouchHelper(object : SimpleCallback(0, RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val confirmDeleteFragment = ConfirmDeleteRideDialog(viewHolder)
                fragmentManager?.let { confirmDeleteFragment.show(it, "deleteConfirm") }
                adapter.notifyDataSetChanged()

            }
        }).attachToRecyclerView(binding.scooterRecyclerView)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}


class ScooterAdapter(private val scooterDB: RidesDB) :
    RecyclerView.Adapter<ScooterAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val NametextView: TextView
        val LocationtextView: TextView
        val TimeStamptextView: TextView
        val SelectButton: Button
        lateinit var scooter: Scooter
        lateinit var scooterDB: RidesDB

        init {
            // Define click listener for the ViewHolder's View
            NametextView = view.findViewById(R.id.scooterName)
            LocationtextView = view.findViewById(R.id.location)
            TimeStamptextView = view.findViewById(R.id.timeStamp)
            SelectButton = view.findViewById(R.id.Select_ride_button)
            NametextView.setOnClickListener {
                Snackbar.make(view, scooter.toString(), Snackbar.LENGTH_SHORT)
                    .setAnchorView(NametextView).show()
            }
            SelectButton.setOnClickListener {
                scooterDB.setCurrentScooter(scooter)
                Snackbar.make(view, "New scooter selected : " + scooter.name, Snackbar.LENGTH_SHORT)
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
        val scooters = scooterDB.getRidesList()
        val scooter = scooters.get(position)
        viewHolder.scooterDB = scooterDB
        viewHolder.scooter = scooter
        viewHolder.NametextView.text = scooter.name
        viewHolder.LocationtextView.text = scooter.location
        viewHolder.TimeStamptextView.text = scooter.convertLongToTime()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = scooterDB.getRidesList().count()
}
