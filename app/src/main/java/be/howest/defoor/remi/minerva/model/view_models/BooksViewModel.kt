package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.*
import be.howest.defoor.remi.minerva.Repositories.BookRepository
import be.howest.defoor.remi.minerva.model.Book
import kotlinx.coroutines.launch

class BooksViewModel(private val bookRepository: BookRepository) : ViewModel() {

    val books: LiveData<List<Book>> = bookRepository.books

    private val _filteredBooks: MutableLiveData<List<Book>> = MutableLiveData<List<Book>>()
    val filteredBooks: LiveData<List<Book>>
        get() = _filteredBooks

    private val _selectedBook: MutableLiveData<Book> = MutableLiveData<Book>()
    val selectedBook: LiveData<Book>
        get() = _selectedBook

    init {
        refreshBooks()
    }

    private fun refreshBooks() {
        viewModelScope.launch {
            try {
                bookRepository.refreshBooks()
                _filteredBooks.value = books.value
            } catch (ex: Exception) {
                _filteredBooks.value = books.value
            }
        }
    }

    fun setSelectedBook(book: Book) {
        _selectedBook.value = book
    }

    fun filterBooks(filter: String) {
        books.value?.let { books ->
            _filteredBooks.value = books.filter { book: Book -> book.title.contains(filter, true) }
        }
    }

}

class BooksViewModelFactory(private val bookRepository: BookRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            return BooksViewModel(bookRepository) as T
        }
        throw IllegalArgumentException("unkown view model class")
    }

}
