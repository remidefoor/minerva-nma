package be.howest.defoor.remi.minerva.model

import android.widget.ImageView

data class Book(
    val isbn: String,
    val img: ImageView,
    val title: String,
    val authors: List<String>,
    val notes: List<String>
)