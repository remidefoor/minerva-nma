package be.howest.defoor.remi.minerva.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "books")
data class Book(
    @PrimaryKey val isbn: String,
    val imageUrl: String,
    val title: String
) : Parcelable
