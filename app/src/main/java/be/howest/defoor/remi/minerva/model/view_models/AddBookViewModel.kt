package be.howest.defoor.remi.minerva.model.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.defoor.remi.minerva.R
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.network.google_books.GoogleBooksApi
import be.howest.defoor.remi.minerva.network.google_books.GoogleBooksApiService
import be.howest.defoor.remi.minerva.network.google_books.Volume
import be.howest.defoor.remi.minerva.network.google_books.VolumeInfo
import kotlinx.coroutines.launch
import java.lang.Exception

class AddBookViewModel : ViewModel() {

    private val _isbn: MutableLiveData<String> = MutableLiveData<String>()
    val isbn: String
        get() = _isbn.value!!

    private val _book: MutableLiveData<Book> = MutableLiveData<Book>()
    val book: LiveData<Book>
        get() = _book

    init {
        _isbn.value = ""
    }

    fun setIsbn(isbn: String) {
        if (this.isbn != isbn) _isbn.value = isbn
    }

    fun getBookInfo() {
        if (_isbn.value?.length == 10 || _isbn.value?.length == 13) {
            viewModelScope.launch {
                try {
                    val volume: Volume = GoogleBooksApi.retrofitService.getBook("isbn:${_isbn.value}")
                    _book.value = mapVolumeToBook(_isbn.value!!, volume)
                } catch (ex: Exception) {

                }
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

}