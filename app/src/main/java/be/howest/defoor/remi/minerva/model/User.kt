package be.howest.defoor.remi.minerva.model

import androidx.room.Entity

@Entity(tableName = "user")
data class User(
    val id: Int,
    val email: String,
    val password: String
)
