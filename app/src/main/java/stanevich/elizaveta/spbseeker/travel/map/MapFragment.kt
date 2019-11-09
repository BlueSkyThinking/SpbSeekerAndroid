package stanevich.elizaveta.spbseeker.travel.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import stanevich.elizaveta.spbseeker.R
import stanevich.elizaveta.spbseeker.databinding.FragmentMapBinding

class MapFragment : Fragment() {

    private val mapViewModel: MapFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(MapFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMapBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_map,
            container,
            false
        )

        binding.lifecycleOwner = this

        binding.mapViewModel = mapViewModel

        return binding.root
    }
}