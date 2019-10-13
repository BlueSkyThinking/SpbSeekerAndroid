package stanevich.elizaveta.spbseeker.location

import android.Manifest
import android.app.AlertDialog
import android.content.Context.LOCATION_SERVICE
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_task.*

class LocationService(
    private val activity: FragmentActivity,
    private val onLocationChange: (Location) -> Unit
) {
    private var locationManager: LocationManager? = null
    private var isRegistered: Boolean = false
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            onLocationChange(location)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        }

        override fun onProviderEnabled(provider: String) {
        }

        override fun onProviderDisabled(provider: String) {
        }
    }

    fun takeALocation() {
        validateCoordinatePermissions()
    }

    fun removeListener() {
        isRegistered = false
        locationManager?.removeUpdates(locationListener)
    }

    private fun validateCoordinatePermissions() {
        if (isRegistered) {
            return
        }

        locationManager = activity.getSystemService(LOCATION_SERVICE) as LocationManager?

        Dexter.withActivity(activity)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    try {
                        val manager = activity.getSystemService(LOCATION_SERVICE) as LocationManager
                        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            val builder = AlertDialog.Builder(activity)
                            builder
                                .setTitle("Enable GPS")
                                .setMessage("Enable it")
                                .setPositiveButton(android.R.string.yes) { _: DialogInterface, _: Int ->
                                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                    activity.startActivityForResult(intent, 0)
                                }
                                .setNegativeButton(android.R.string.no) { _: DialogInterface, _: Int -> }
                                .show()
                        } else {
                            locationManager?.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                0L, 0f, locationListener
                            )
                            locationManager?.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                0L, 0f, locationListener
                            )
                            isRegistered = true
                        }
                    } catch (ex: SecurityException) {
                        Log.d("LocationError", "Security Exception, no location available");
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    AlertDialog.Builder(activity)
                        .setTitle("Get Location Permission")
                        .setMessage("Get It")
                        .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                            dialog.dismiss()
                            token?.cancelPermissionRequest()
                        }
                        .setPositiveButton(android.R.string.ok) { dialog, _ ->
                            dialog.dismiss()
                            token?.continuePermissionRequest()
                        }
                        .setOnDismissListener { token?.cancelPermissionRequest() }
                        .show()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Snackbar.make(
                        activity.gradientRect!!,
                        "ACCESS IS DENIED!!",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            })
            .check()
    }

    fun processGpsActivityResultFunction() {
        val manager = activity.getSystemService(LOCATION_SERVICE) as LocationManager
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) &&
            ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                0f,
                locationListener
            )
        } else {
            AlertDialog.Builder(activity)
                .setTitle("GPS still Disabled")
                .setMessage("Enable it")
                .setPositiveButton(android.R.string.ok, { dialog, _ -> })
                .show()
        }
    }
}