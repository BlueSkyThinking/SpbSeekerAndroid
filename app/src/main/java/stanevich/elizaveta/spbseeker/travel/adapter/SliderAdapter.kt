package stanevich.elizaveta.spbseeker.travel.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import stanevich.elizaveta.spbseeker.travel.ar.ARFragment
import stanevich.elizaveta.spbseeker.travel.map.MapFragment
import stanevich.elizaveta.spbseeker.travel.travel.TravelFragment

class SliderAdapter(private val fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ARFragment()
            1 -> TravelFragment()
            else -> MapFragment()
        }

    }

    override fun getCount(): Int {
        return 3
    }

}