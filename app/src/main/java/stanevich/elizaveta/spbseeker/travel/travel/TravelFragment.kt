package stanevich.elizaveta.spbseeker.travel.travel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import stanevich.elizaveta.spbseeker.R
import stanevich.elizaveta.spbseeker.databinding.FragmentTravelBinding


class TravelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTravelBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_travel,
            container,
            false
        )
        val application = requireNotNull(this.activity).application


        binding.lifecycleOwner = this

        val viewModelFactory =
            TravelFragmentFactory(
                application
            )

        val travelViewModel =
            ViewModelProviders.of(this, viewModelFactory)
                .get(TravelFragmentViewModel::class.java)

        binding.travelViewModel = travelViewModel

        return binding.root
    }
}