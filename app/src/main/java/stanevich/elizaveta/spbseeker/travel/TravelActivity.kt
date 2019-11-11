package stanevich.elizaveta.spbseeker.travel

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.ar.core.ArCoreApk
import stanevich.elizaveta.spbseeker.R
import stanevich.elizaveta.spbseeker.databinding.ActivityTravelBinding
import stanevich.elizaveta.spbseeker.travel.adapter.SliderAdapter


class TravelActivity : AppCompatActivity() {
    private var arAvailable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        val binding: ActivityTravelBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_travel)

        arAvailable = checkArAvailability()
        val sliderAdapter = SliderAdapter(supportFragmentManager, arAvailable)
        binding.apply {
            viewpager.apply {
                adapter = sliderAdapter
                currentItem = if (arAvailable) 1 else 0
            }
            tabDots.setupWithViewPager(viewpager, true)
        }

        binding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (arAvailable) {
                    when (position) {
                        0 -> binding.apply {
                            bottomNavigation.visibility = View.GONE
                            toolbar.visibility = View.GONE
                        }
                        2 -> binding.apply {
                            bottomNavigation.visibility = View.VISIBLE
                            toolbar.visibility = View.VISIBLE
                            textStart.setTextColor(Color.parseColor("#2B0DA3"))
                            textEnd.setTextColor(Color.parseColor("#CB0A15"))
                            indicatorDiagram.setColorFilter(
                                ContextCompat.getColor(
                                    applicationContext!!,
                                    R.color.colorBlue
                                )
                            )
                            backgroundRoundIndicator.setColorFilter(
                                ContextCompat.getColor(
                                    applicationContext!!,
                                    R.color.colorBlue
                                )
                            )
//                            btnPuzzle.setColorFilter(
//                                ContextCompat.getColor(
//                                    applicationContext!!,
//                                    R.color.colorBlue
//                                )
//                            )
                            btnSettings.setColorFilter(
                                ContextCompat.getColor(
                                    applicationContext!!,
                                    R.color.colorBlue
                                )
                            )
                            btnVectorBack.setColorFilter(
                                ContextCompat.getColor(
                                    applicationContext!!,
                                    R.color.colorBlue
                                )
                            )
                            indicator.setBackgroundColor(Color.parseColor("#CB0A15"))
                            textDistance.setTextColor(Color.parseColor("#CB0A15"))

                        }
                        else -> binding.apply {
                            bottomNavigation.visibility = View.VISIBLE
                            toolbar.visibility = View.VISIBLE
                            textStart.setTextColor(Color.parseColor("#ffffff"))
                            textEnd.setTextColor(Color.parseColor("#ffffff"))
                            indicatorDiagram.setColorFilter(
                                ContextCompat.getColor(
                                    applicationContext!!,
                                    R.color.colorAccent
                                )
                            )
                            backgroundRoundIndicator.setColorFilter(
                                ContextCompat.getColor(
                                    applicationContext!!,
                                    R.color.colorAccent
                                )
                            )
//                            btnPuzzle.setColorFilter(
//                                ContextCompat.getColor(
//                                    applicationContext!!,
//                                    R.color.colorAccent
//                                )
//                            )
                            btnSettings.setColorFilter(
                                ContextCompat.getColor(
                                    applicationContext!!,
                                    R.color.colorAccent
                                )
                            )
                            btnVectorBack.setColorFilter(
                                ContextCompat.getColor(
                                    applicationContext!!,
                                    R.color.colorAccent
                                )
                            )
                            indicator.setBackgroundColor(Color.parseColor("#ffffff"))
                            textDistance.setTextColor(Color.parseColor("#ffffff"))
                        }
                    }

                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun checkArAvailability(): Boolean {
        val availability = ArCoreApk.getInstance().checkAvailability(this)

        return availability.isSupported

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

}
