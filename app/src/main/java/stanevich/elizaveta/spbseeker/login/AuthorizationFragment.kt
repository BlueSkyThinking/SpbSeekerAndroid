package stanevich.elizaveta.spbseeker.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import stanevich.elizaveta.spbseeker.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAuthorizationBinding.inflate(inflater)

        return binding.root
    }
}