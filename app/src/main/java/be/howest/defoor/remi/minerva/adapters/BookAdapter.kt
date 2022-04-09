package be.howest.defoor.remi.minerva.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.model.Book

class BookAdapter(
    private val books: List<Book>,
    private val bookClickListener: (String) -> Unit
    ) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(
        private val view: View,
        clickAtPosition: (Int) -> Unit
        ) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.book_cover)
        val textView: TextView = view.findViewById(R.id.book_title)

        init {
            view.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val adapterLayout: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.book, parent, false)
        return BookViewHolder(adapterLayout) {
            bookClickListener(books[it].title)
        }
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: Book = books[position]
        holder.imageView.setImageResource(book.imageUrl)
        holder.textView.text = book.title
    }

    override fun getItemCount(): Int {
        return books.size
    }
}