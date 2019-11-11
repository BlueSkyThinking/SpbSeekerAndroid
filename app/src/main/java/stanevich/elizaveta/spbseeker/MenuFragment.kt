package stanevich.elizaveta.spbseeker.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import stanevich.elizaveta.spbseeker.databinding.FragmentMenuBinding
import stanevich.elizaveta.spbseeker.travel.TravelActivity

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentMenuBinding.inflate(inflater)

        binding.apply {
            fabArchitecture.setOnClickListener { view: View ->
                startActivity(Intent(context, TravelActivity::class.java))
            }
        }
        return binding.root
    }


}