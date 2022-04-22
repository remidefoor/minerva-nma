package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.model.Book

class AddBookViewModel : ViewModel() {

    private val _isbn: MutableLiveData<String> = MutableLiveData<String>()
    val isbn: LiveData<String>
        get() = _isbn

    private var book: Book

    init {
        _isbn.value = ""
        book = Book("9789076174105", "http://books.google.com/books/content?id=VHpUPgAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api", "Harry Potter en de steen der wijzen", listOf("Joanne Kathleen Rowling"))
    }

    fun setIsbn(isbn: String) {
        _isbn.value = isbn
    }

    fun setBook(book: Book) {
        this.book = book
    }

    fun getBook(): Book {
        return book
    }

}