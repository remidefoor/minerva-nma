package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.*
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.User
import be.howest.defoor.remi.minerva.network.google_books.GoogleBooksApi
import be.howest.defoor.remi.minerva.network.google_books.Volume
import be.howest.defoor.remi.minerva.network.google_books.VolumeInfo
import be.howest.defoor.remi.minerva.network.minerva.MinervaApi
import be.howest.defoor.remi.minerva.network.minerva.UserBook
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AddBookViewModel(private val userRepository: UserRepository) : ViewModel() {

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
        viewModelScope.launch {
            try {
                validateIsbn()
                val volume: Volume = GoogleBooksApi.retrofitService.getBook("isbn:${_isbn.value}")
                _book.value = mapVolumeToBook(_isbn.value!!, volume)
            } catch (ex: IllegalStateException) {
                // TODO display error message
            } catch (ex: IllegalArgumentException) {
                // TODO display error message
            } catch (ex: Exception) {
                // TODO display api errors
            }
        }
    }

    private fun validateIsbn() {
        if (_isbn.value?.length == 0) {
            throw IllegalStateException("And ISBN must be provided.")
        }

        if (_isbn.value?.length != 10 && _isbn.value?.length != 13) {
            throw IllegalArgumentException("The ISBN must consist of 10 or 13 digits.")
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

    fun postBook() {
        _book.value?.let {
            viewModelScope.launch {
                try {
                    val user: User = userRepository.user.first()
                    MinervaApi.retrofitService.postUserBook(user.id, UserBook(it.isbn))
                } catch (ex: Exception) {
                    // TODO display api errors
                }
            }
        }
    }

}

class AddBookViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddBookViewModel::class.java)) {
            return AddBookViewModel(userRepository) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }

}
