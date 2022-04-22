package be.howest.defoor.remi.minerva.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val isbn: String,
    val imageUrl: String,
    val title: String,
    val authors: List<String>
) : Parcelable
