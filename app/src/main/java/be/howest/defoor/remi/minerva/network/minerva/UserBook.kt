package be.howest.defoor.remi.minerva.network.minerva

import com.squareup.moshi.Json

data class UserBook (
    @Json(name = "ISBN") val isbn: String
)
