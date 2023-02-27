package dk.itu.moapd.scootersharing.tokj.Pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.scootersharing.tokj.Model.RidesDB
import dk.itu.moapd.scootersharing.tokj.databinding.FragmentStartRideBinding


/**
 * A simple [Fragment] subclass.
 * Use the [StartRideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartRideFragment : Fragment() {

    companion object {
        lateinit var ridesDB: RidesDB
    }

    private lateinit var binding: FragmentStartRideBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartRideBinding.inflate(layoutInflater)
        ridesDB = RidesDB.get(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.letsRideButton.setOnClickListener {
            if (binding.editScooterID.text.isNotEmpty() &&
                binding.editLocationText.text.isNotEmpty()
            ) {
                val name = binding.editScooterID.text.toString().trim()
                val location = binding.editLocationText.text.toString().trim()
                val foundBike =
                    ridesDB.getRidesList()
                        .find { it.name == name && it.location == location }
                if (foundBike == null) showScooterSnackBar(
                    binding.root,
                    binding.letsRideButton,
                    "Ride not found",
                )
                else {
                    ridesDB.setCurrentScooter(foundBike)
                    showScooterSnackBar(
                        binding.root,
                        binding.letsRideButton,
                        "CurrentScooter Set as $foundBike"
                    )
                }
                binding.editLocationText.text.clear()
                binding.editScooterID.text.clear()
            }
        }
    }

    fun showScooterSnackBar(context: View, anchor: View, text: String) {
        Snackbar.make(context, text, Snackbar.LENGTH_SHORT).setAnchorView(anchor)
            .show()
    }


}