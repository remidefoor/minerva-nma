package be.howest.defoor.remi.minerva.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.model.Book

class ItemAdapter(private val books: List<Book>) {

    class BookViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.book_cover)
        val textView: TextView = view.findViewById(R.id.book_title)
    }
}