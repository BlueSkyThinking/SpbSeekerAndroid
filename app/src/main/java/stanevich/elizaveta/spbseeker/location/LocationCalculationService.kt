package stanevich.elizaveta.spbseeker.location

import android.location.Location
import kotlin.math.abs

class LocationCalculationService {
    fun calculateDistance(source: Location, destination: Location): Float {
        return source.distanceTo(destination)
    }
}