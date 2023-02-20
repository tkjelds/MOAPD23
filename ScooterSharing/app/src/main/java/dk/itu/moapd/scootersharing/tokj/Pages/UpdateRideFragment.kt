package dk.itu.moapd.scootersharing.tokj.Pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.tokj.Model.RidesDB
import dk.itu.moapd.scootersharing.tokj.R
import dk.itu.moapd.scootersharing.tokj.databinding.ActivityUpdateRideBinding
import dk.itu.moapd.scootersharing.tokj.databinding.FragmentUpdateRideBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateRideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private lateinit var binding: FragmentUpdateRideBinding

class UpdateRideFragment : Fragment() {
    companion object {
        lateinit var ridesDB: RidesDB
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateRideBinding.inflate(layoutInflater)
        ridesDB = RidesDB.get(requireActivity())
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editScooterID.setText(ridesDB.getCurrentScooter().name.trim())

        binding.updateRideButton.setOnClickListener {
            if (binding.editScooterID.text.isNotEmpty() &&
                binding.editLocationText.text.isNotEmpty()
            ) {
                val location = binding.editLocationText.text.toString().trim()
                ridesDB.updateCurrentScooter(location)
                showScooterSnackBar(
                    binding.root,
                    binding.updateRideButton,
                    ridesDB.getCurrentScooter().toString()
                )
            }
        }
    }

    fun showScooterSnackBar(context: View, anchor: View, text: String) {
        Snackbar.make(context, text, Snackbar.LENGTH_SHORT).setAnchorView(anchor)
            .show()
    }

}