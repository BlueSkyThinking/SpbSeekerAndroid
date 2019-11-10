package stanevich.elizaveta.spbseeker.travel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import stanevich.elizaveta.spbseeker.R
import stanevich.elizaveta.spbseeker.databinding.ActivityTravelBinding
import stanevich.elizaveta.spbseeker.travel.adapter.SliderAdapter
import stanevich.elizaveta.spbseeker.travel.bottom.BottomSheetFragment

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
//        showBottomSheetDialogFragment()

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
