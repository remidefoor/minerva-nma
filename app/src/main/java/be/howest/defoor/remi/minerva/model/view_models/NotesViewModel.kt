package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.*
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.Note
import be.howest.defoor.remi.minerva.model.User
import be.howest.defoor.remi.minerva.network.minerva.MinervaApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class NotesViewModel(private val userRepository: UserRepository, book: Book) : ViewModel() {

    private val _book: MutableLiveData<Book> = MutableLiveData<Book>()
    val book: LiveData<Book>
        get() = _book

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>>
        get() = _notes

    init {
        _book.value = book
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            try {
                val user: User = userRepository.user.first()
                _notes.value = MinervaApi.retrofitService.getNotes(user.id, _book.value?.isbn!!)
            } catch (ex: Exception) {
                _notes.value = emptyList()
            }
        }
    }

}

class NotesViewModelFactory(private val userRepository: UserRepository, private val book: Book) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(userRepository, book) as T
        }
        throw IllegalArgumentException("Unknown view model class.")
    }

}
