package stanevich.elizaveta.spbseeker

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import stanevich.elizaveta.spbseeker.databinding.FragmentStartTravelBinding


class StartTravelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStartTravelBinding.inflate(inflater)

        binding.apply {
            tvStartTravel.setOnClickListener { view: View ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_startTravelFragment_to_loginFragment)
            }

            val uri =
                Uri.parse("android.resource://" + activity!!.packageName + "/" + R.raw.background)
            video.apply {
                setVideoURI(uri)
                start()
                setOnPreparedListener(MediaPlayer.OnPreparedListener { mp -> mp.isLooping = true })
            }




            return binding.root
        }
    }
}
