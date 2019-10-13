package com.komdosh.spbseeker.service.location

import android.location.Location
import kotlin.math.abs

class LocationCalculationService {
    fun calculateDistance(source: Location, destination: Location): Float {
        return source.distanceTo(destination)
    }
}