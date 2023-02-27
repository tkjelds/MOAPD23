package dk.itu.moapd.scootersharing.tokj

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import dk.itu.moapd.scootersharing.tokj.Pages.MainFragment
import dk.itu.moapd.scootersharing.tokj.Pages.ScooterAdapter

class ConfirmDeleteRideDialog(_viewHolder: RecyclerView.ViewHolder) : DialogFragment() {
    private lateinit var viewHolder: RecyclerView.ViewHolder

    init {
        viewHolder = _viewHolder
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val scooterID = viewHolder.adapterPosition
            val scooterToBeDeleted = MainFragment.ridesDB.getScooterFromID(scooterID)
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Delete : $scooterToBeDeleted")
                .setPositiveButton(
                    "Yes"
                ) { dialog, id ->
                    MainFragment.ridesDB.deleteRide(scooterToBeDeleted)
                    MainFragment.adapter.notifyItemRemoved(scooterID)
                }
                .setNegativeButton(
                    "No"
                ) { dialog, id ->
                    //
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}