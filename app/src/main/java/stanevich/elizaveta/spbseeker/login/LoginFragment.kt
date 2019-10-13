package stanevich.elizaveta.spbseeker.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import stanevich.elizaveta.spbseeker.R
import stanevich.elizaveta.spbseeker.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLoginBinding.inflate(inflater)

        binding.apply {
            fabButton.setOnClickListener { view: View ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_loginFragment_to_menuFragment)
            }
        }

        return binding.root
    }


}