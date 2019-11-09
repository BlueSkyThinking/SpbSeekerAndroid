package stanevich.elizaveta.spbseeker.travel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import stanevich.elizaveta.spbseeker.R
import stanevich.elizaveta.spbseeker.databinding.ActivityTravelBinding
import stanevich.elizaveta.spbseeker.travel.adapter.SliderAdapter

class TravelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTravelBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_travel)

        val sliderAdapter = SliderAdapter(supportFragmentManager)
        binding.apply {
            viewpager.apply {
                adapter = sliderAdapter
                currentItem = 1
            }
            tabDots.setupWithViewPager(viewpager, true)
        }

    }
}
