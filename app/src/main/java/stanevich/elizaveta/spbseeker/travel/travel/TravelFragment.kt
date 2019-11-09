package stanevich.elizaveta.spbseeker.travel.travel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import stanevich.elizaveta.spbseeker.databinding.FragmentTravelBinding


class TravelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTravelBinding.inflate(inflater)

        return binding.root
    }
}