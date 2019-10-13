package stanevich.elizaveta.spbseeker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import stanevich.elizaveta.spbseeker.databinding.FragmentStartTravelBinding

class StartTravelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStartTravelBinding.inflate(inflater)

        binding.apply {
            tvStartTravel.setOnClickListener { view: View ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_startTravelFragment_to_taskFragment)
            }

            return binding.root
        }
    }
}
