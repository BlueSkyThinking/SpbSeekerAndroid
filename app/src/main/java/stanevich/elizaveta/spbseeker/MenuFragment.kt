package stanevich.elizaveta.spbseeker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import stanevich.elizaveta.spbseeker.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentMenuBinding.inflate(inflater)

        binding.apply {
            imageview.setOnClickListener { view: View ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_menuFragment_to_taskFragment)
            }
        }

        return binding.root
    }
}