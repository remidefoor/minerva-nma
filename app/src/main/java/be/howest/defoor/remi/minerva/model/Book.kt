package be.howest.defoor.remi.minerva.model

import android.widget.ImageView

data class Book(
    val isbn: String,
    val image: ImageView,
    val title: String,
    val authors: List<String>
)