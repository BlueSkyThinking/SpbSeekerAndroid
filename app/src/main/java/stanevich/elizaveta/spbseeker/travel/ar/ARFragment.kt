package stanevich.elizaveta.spbseeker.travel.ar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import stanevich.elizaveta.spbseeker.R
import stanevich.elizaveta.spbseeker.databinding.FragmentArBinding

class ARFragment : Fragment() {

    private val arViewModel: ARFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(ARFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentArBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_ar,
            container,
            false
        )

        binding.lifecycleOwner = this

        binding.arViewModel = arViewModel

        return binding.root
    }
}