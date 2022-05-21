package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.*
import be.howest.defoor.remi.minerva.Repositories.BookRepository
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.User
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ProfileViewModel(private val userRepository: UserRepository,
                       private val bookRepository: BookRepository): ViewModel() {

    val user: LiveData<User> = userRepository.user.asLiveData()

    private val _loggedIn: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean>
        get() = _loggedIn

    init {
        _loggedIn.value = true
    }

    fun signOff() {
        viewModelScope.launch {
            userRepository.deleteUser(user.value!!)
            removeCachedBooks()
            _loggedIn.value = false
        }
    }

    private fun removeCachedBooks() {
        viewModelScope.launch {
            val books: List<Book>? = bookRepository.books.value
            if (books != null) {
                for (book: Book in books) {
                    bookRepository.deleteBook(book)
                }
            }
        }
    }

}

class ProfileViewModelFactory(private val userRepository: UserRepository,
                              private val bookRepository: BookRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(userRepository, bookRepository) as T
        }
        throw IllegalArgumentException("unkown view model class")
    }

}
