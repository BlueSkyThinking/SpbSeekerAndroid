package stanevich.elizaveta.spbseeker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import stanevich.elizaveta.spbseeker.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentMenuBinding.inflate(inflater)

        return binding.root
    }
}