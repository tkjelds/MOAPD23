@file:Suppress("DEPRECATION")

package dk.itu.moapd.scootersharing.tokj.Pages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import dk.itu.moapd.scootersharing.tokj.ConfirmDeleteRideDialog
import dk.itu.moapd.scootersharing.tokj.Model.RidesDB
import dk.itu.moapd.scootersharing.tokj.databinding.FragmentMainBinding


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


