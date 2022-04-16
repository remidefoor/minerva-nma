package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.Note
import be.howest.defoor.remi.minerva.network.MinervaApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class BookViewModel : ViewModel() {

    private var books: List<Book>

    fun setBooks(books: List<Book>) {
        this.books = books
    }

    fun getBooks(): List<Book> {
        return books
    }

    fun getFilteredBooks(query: String): List<Book> {
        return books.filter { book: Book -> book.title.contains(query, ignoreCase = true) }
    }


    private var book: Book

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>>
        get() = _notes

    private val _note: MutableLiveData<String> = MutableLiveData<String>()
    val note: LiveData<String>
        get() = _note

    init {
        // TODO refactor
        books = emptyList()
        book = Book("9789076174105", R.drawable.harry_potter_and_the_philosopher_s_stone_book_cover, "Harry Potter en de steen der wijzen", listOf("Joanne Kathleen Rowling"))
        _notes.value = emptyList()
        _note.value = ""
    }

    fun setBook(newBook: Book) {
        this.book = newBook
        setNotes()
    }

    fun getBook(): Book {
        return book
    }

    private fun setNotes() {
        viewModelScope.launch {
            try {
                _notes.value = MinervaApi.retrofitService.getNotes(1, book.isbn)
            } catch (ex: Exception) {
                // TODO handle exceptions
            }
        }
    }

    private fun setNote(note: String) {
        _note.value = note
    }

    private fun clearNote() {
        _note.value = ""
    }

}
