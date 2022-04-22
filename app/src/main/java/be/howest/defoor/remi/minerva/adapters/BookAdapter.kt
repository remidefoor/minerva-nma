package be.howest.defoor.remi.minerva.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.howest.defoor.remi.minerva.databinding.BookBinding
import be.howest.defoor.remi.minerva.model.Book

class BookAdapter(val listener: BookListener) : ListAdapter<Book, BookAdapter.BookViewHolder>(DiffCallback) {

    class BookViewHolder(private val binding: BookBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book, listener: BookListener) {
            binding.book = book
            binding.listener = listener
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(BookBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book: Book = getItem(position)
        holder.bind(book, listener)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.isbn == newItem.isbn
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

}

class BookListener(val clickListener: (book: Book) -> Unit) {

    fun onClick(book: Book) = clickListener(book)

}
