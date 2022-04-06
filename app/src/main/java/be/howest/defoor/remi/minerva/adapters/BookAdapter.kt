package be.howest.defoor.remi.minerva.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.model.BookDetail

class BookAdapter(private val books: List<BookDetail>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.book_cover)
        val textView: TextView = view.findViewById(R.id.book_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val adapterLayout: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.book, parent, false)
        return BookViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: BookDetail = books[position]
        // TODO("replace image")
        holder.textView.text = book.title
    }

    override fun getItemCount(): Int {
        return books.size
    }
}