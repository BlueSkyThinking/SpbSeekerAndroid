package stanevich.elizaveta.spbseeker.travel.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import stanevich.elizaveta.spbseeker.travel.ARFragment
import stanevich.elizaveta.spbseeker.travel.FragmentMap
import stanevich.elizaveta.spbseeker.travel.TravelFragment

class SliderAdapter(private val fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ARFragment()
            1 -> TravelFragment()
            else -> FragmentMap()
        }

    }

    override fun getCount(): Int {
        return 3
    }

}