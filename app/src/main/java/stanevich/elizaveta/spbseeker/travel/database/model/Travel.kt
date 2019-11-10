package stanevich.elizaveta.spbseeker.travel.database.model

import java.util.*

data class Travel(
    val id: Long?,
    var name: String,
    var adminId: Long,
    var imgUrl: String,
    var description: String,
    var categoryId: Long,
    val createdDate: Date?,
    val updatedDate: Date?
)