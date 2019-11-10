package stanevich.elizaveta.spbseeker.travel.database.model

import java.util.*

data class Category(
    val id: Long?,
    var name: String,
    var imgUrl: String,
    private var adminId: Long,
    val createdDate: Date?,
    val updatedDate: Date?
)