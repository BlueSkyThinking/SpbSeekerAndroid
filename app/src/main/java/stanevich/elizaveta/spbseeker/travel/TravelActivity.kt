package stanevich.elizaveta.spbseeker.travel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.ar.core.ArCoreApk
import stanevich.elizaveta.spbseeker.R
import stanevich.elizaveta.spbseeker.databinding.ActivityTravelBinding
import stanevich.elizaveta.spbseeker.travel.adapter.SliderAdapter
import stanevich.elizaveta.spbseeker.travel.bottom.BottomSheetFragment

class TravelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTravelBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_travel)

        val arAvailable = checkArAvailability()
        val sliderAdapter = SliderAdapter(supportFragmentManager, arAvailable)
        binding.apply {
            viewpager.apply {
                adapter = sliderAdapter
                currentItem = if (arAvailable) 1 else 0
            }
            tabDots.setupWithViewPager(viewpager, true)
        }
//        showBottomSheetDialogFragment()

    }

    private fun checkArAvailability(): Boolean {
        val availability = ArCoreApk.getInstance().checkAvailability(this)

        return availability.isSupported

    }

    private fun showBottomSheetDialogFragment() {
        val bottomSheetFragment = BottomSheetFragment()
        bottomSheetFragment.setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.SheetDialog)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    private fun showBottomSheetDialog() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }
}
