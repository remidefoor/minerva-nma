package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.network.google_books.GoogleBooksApi
import be.howest.defoor.remi.minerva.network.google_books.Volume
import be.howest.defoor.remi.minerva.network.google_books.VolumeInfo
import be.howest.defoor.remi.minerva.network.minerva.MinervaApi
import be.howest.defoor.remi.minerva.network.minerva.UserBook
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    private val _books: MutableLiveData<List<Book>> = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
        get() = _books

    private val _filteredBooks: MutableLiveData<List<Book>> = MutableLiveData<List<Book>>()
    val filteredBooks: LiveData<List<Book>>
        get() = _filteredBooks

    private val _selectedBook: MutableLiveData<Book> = MutableLiveData<Book>()
    val selectedBook: LiveData<Book>
        get() = _selectedBook

    init {
        getAllBooks()
    }

    private fun getAllBooks() {
        viewModelScope.launch {
            try {
                val userBooks: List<UserBook> = MinervaApi.retrofitService.getUserBooks(1)
                val books: MutableList<Book> = mutableListOf()
                for (userBook: UserBook in userBooks) {
                    val volume: Volume = GoogleBooksApi.retrofitService.getBook("isbn:${userBook.isbn}")
                    val book: Book = mapVolumeToBook(userBook.isbn, volume)
                    books.add(book)
                }
                setAllBooks(books)
            } catch (ex: Exception) {
                setAllBooks(emptyList())
            }
        }
    }

    private fun mapVolumeToBook(isbn: String, volume: Volume): Book {
        val volumeInfo: VolumeInfo = volume.items.first().volumeInfo
        return Book(
            isbn,
            volumeInfo.imageLinks.thumbnail,
            volumeInfo.title,
            volumeInfo.authors
        )
    }

    private fun setAllBooks(books: List<Book>) {
        _books.value = books
        _filteredBooks.value = books
    }

    fun setSelectedBook(book: Book) {
        _selectedBook.value = book
    }

    fun filterBooks(filter: String) {
        _books.value?.let { books ->
            _filteredBooks.value = books.filter { book: Book -> book.title.contains(filter, true) }
        }
    }

}
