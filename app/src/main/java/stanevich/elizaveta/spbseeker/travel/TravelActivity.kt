package stanevich.elizaveta.spbseeker.travel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import stanevich.elizaveta.spbseeker.R
import stanevich.elizaveta.spbseeker.travel.adapter.SliderAdapter

class TravelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel)

        val adapter = SliderAdapter(supportFragmentManager)
        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter = adapter
        viewPager.currentItem = 1
    }
}
