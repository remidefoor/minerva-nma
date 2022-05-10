package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.network.minerva.MinervaApi
import be.howest.defoor.remi.minerva.network.minerva.UserBook
import kotlinx.coroutines.launch
import java.lang.Exception

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
        // TODO get books from api
        val books: List<Book> = listOf(
            Book("9789076174105", "http://books.google.com/books/content?id=VHpUPgAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api", "Harry Potter en de steen der wijzen", listOf("Joanne Kathleen Rowling")),
            Book("9789076174105", "http://books.google.com/books/content?id=LUJvGQAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api", "Harry Potter en de geheime kamer", listOf("Joanne Kathleen Rowling")),
            Book("9789076174105", "http://books.google.com/books/content?id=jxPCGAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api", "Harry Potter en de gevangene van Azkaban", listOf("Joanne Kathleen Rowling")),
            Book("9789076174105", "http://books.google.com/books/content?id=jUcvPQAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api", "Harry Potter en de vuurbeker", listOf("Joanne Kathleen Rowling")),
            Book("9789076174105", "http://books.google.com/books/content?id=GMXKGAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api", "Harry Potter en de Orde van de Feniks", listOf("Joanne Kathleen Rowling")),
        )
        viewModelScope.launch {
            try {
                println("in")
                val userBooks: List<UserBook> = MinervaApi.retrofitService.getUserBooks(1);
                println(userBooks)
                println("worked")
            } catch (ex: Exception) {

            }
        }
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
