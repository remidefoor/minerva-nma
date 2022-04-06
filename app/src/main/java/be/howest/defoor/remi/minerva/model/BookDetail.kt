package be.howest.defoor.remi.minerva.model

import android.widget.ImageView

data class BookDetail(
    val isbn: String,
    val imageUrl: String,
    val title: String,
    val authors: List<String>
)