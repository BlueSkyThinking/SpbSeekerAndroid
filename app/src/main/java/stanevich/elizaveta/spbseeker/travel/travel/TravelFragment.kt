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
import stanevich.elizaveta.spbseeker.travel.bottom.BottomSheetFragment


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

//        binding.text.setOnClickListener {
//            showBottomSheetDialogFragment()
//        }




        return binding.root
    }

    private fun showBottomSheetDialogFragment() {
        val bottomSheetFragment = BottomSheetFragment()
//        bottomSheetFragment.setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.SheetDialog)
        bottomSheetFragment.show(fragmentManager!!, bottomSheetFragment.tag)
//        bottomSheetFragment.s
    }


}