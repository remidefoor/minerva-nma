package be.howest.defoor.remi.minerva.adapters

import android.net.Uri
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.model.Book
import coil.load

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imageUri: Uri? = imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imageUri) {
            placeholder(R.drawable.blank_book_cover)
            error(R.drawable.loading_animation)
        }
    }
}

@BindingAdapter("listBooks")
fun bindBooksRecyclerView(recyclerView: RecyclerView, books: List<Book>) {
    val adapter: BookAdapter = recyclerView.adapter as BookAdapter
    adapter.submitList(books)
}
