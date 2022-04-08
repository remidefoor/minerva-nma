package be.howest.defoor.remi.minerva.model

import android.widget.ImageView

data class Book(
    val isbn: String,
    val imageUrl: Int,
    val title: String,
    val authors: List<String>,
    val notes: List<Note> = emptyList()
)
