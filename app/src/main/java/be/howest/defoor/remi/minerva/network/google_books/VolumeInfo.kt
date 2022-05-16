package be.howest.defoor.remi.minerva.network.google_books

data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val imageLinks: ImageUrl = ImageUrl("")
)
