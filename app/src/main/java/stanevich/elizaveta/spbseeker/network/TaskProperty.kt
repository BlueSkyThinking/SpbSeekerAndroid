package stanevich.elizaveta.spbseeker.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class TaskProperty (
    val id: Long?,
    var name: String,
    var adminId: Long,
    var imgUrlList: List<String>,
    var description: String,
    var shortDescription: String,
    var orderNum: Long,
    var latitude: Double,
    var longitude: Double,
    val createdDate: Date?,
    val updatedDate: Date?,
    val travelId: Long?
) : Parcelable