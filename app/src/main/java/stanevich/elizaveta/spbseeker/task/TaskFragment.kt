package stanevich.elizaveta.spbseeker.task

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_task.*
import stanevich.elizaveta.spbseeker.R
import stanevich.elizaveta.spbseeker.databinding.FragmentTaskBinding
import stanevich.elizaveta.spbseeker.location.LocationCalculationService
import stanevich.elizaveta.spbseeker.location.LocationService
import java.util.*

class TaskFragment : Fragment() {

    private lateinit var locationService: LocationService
    private lateinit var scheduler: Timer

    private fun resolveResult(
        minDist: Float,
        lcs: LocationCalculationService,
        center: Location,
        currentLocation: Location
    ) {

    }

    private fun changeIndicatorPosition(minDist: Float) {
        val indicatorMargin = calculateIndicatorMargin(minDist)
        // distanceText.text = getString(R.string.distanceText, minDist)

        if (gradientRect.measuredWidth > indicatorMargin) {
            val indicatorLayout = indicatorIcon.layoutParams as ConstraintLayout.LayoutParams
            indicatorLayout.marginStart = indicatorMargin.toInt()
            indicatorIcon.layoutParams = indicatorLayout
        }
    }

    private fun calculateIndicatorMargin(minDist: Float): Float {
        val thresholdDistance = 10000
        var indicatorMargin = (thresholdDistance - minDist)

        val metrics = getResources().getDisplayMetrics();

        indicatorMargin = if (indicatorMargin < 0) 0.0f else indicatorMargin
        indicatorMargin /= thresholdDistance
        indicatorMargin *= gradientRect.measuredWidth
        val minWidthMargin = metrics.densityDpi * 0.12
        val maxWidthMargin = metrics.densityDpi * 2.34
        indicatorMargin =
            if (indicatorMargin < minWidthMargin) minWidthMargin.toFloat() else indicatorMargin
        indicatorMargin =
            if (indicatorMargin > maxWidthMargin) maxWidthMargin.toFloat() else indicatorMargin
        return indicatorMargin
    }


    private fun coordinate() {
        locationService.takeALocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTaskBinding.inflate(inflater)

        binding.apply {
            btnVectorBack.setOnClickListener { view: View ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_taskFragment_to_loginFragment)
            }
        }

            binding.lifecycleOwner = this

            val application = requireNotNull(this.activity).application

            val viewModelFactory = TaskViewModelFactory(application)

            val taskViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(TaskViewModel::class.java)

            binding.taskViewModel = taskViewModel

            locationService = LocationService(activity!!) { currentLocation ->
                val lcs = LocationCalculationService()
                val center = Location("")
                center.latitude = 59.9860
                center.longitude = 30.178123
                val minDist = lcs.calculateDistance(currentLocation, center)

                resolveResult(minDist, lcs, center, currentLocation)

                changeIndicatorPosition(minDist)
            }

            // scheduler = fixedRateTimer("locationCheck", period = 1000) {
            coordinate()
            //}

            return binding.root
        }
    }
