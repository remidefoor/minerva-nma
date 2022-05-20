package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.*
import be.howest.defoor.remi.minerva.Repositories.UserRepository
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.Note
import be.howest.defoor.remi.minerva.model.User
import be.howest.defoor.remi.minerva.network.minerva.Id
import be.howest.defoor.remi.minerva.network.minerva.MinervaApi
import be.howest.defoor.remi.minerva.network.minerva.NoteText
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.*

class NotesViewModel(private val userRepository: UserRepository, book: Book) : ViewModel() {

    private val _book: MutableLiveData<Book> = MutableLiveData<Book>()
    val book: LiveData<Book>
        get() = _book

    private val _notes: MutableLiveData<MutableList<Note>> = MutableLiveData<MutableList<Note>>()
    val notes: LiveData<MutableList<Note>>
        get() = _notes

    private val _note: MutableLiveData<String> = MutableLiveData<String>()
    val note: String
        get() = _note.value!!

    init {
        _book.value = book
        getAllNotes()
        _note.value = ""
    }

    fun setNote(note: String) {
        if (_note.value != note) _note.value = note
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            try {
                val user: User = userRepository.user.first()
                _notes.value = LinkedList(MinervaApi.retrofitService.getNotes(user.id, _book.value?.isbn!!))
            } catch (ex: Exception) {
                _notes.value = LinkedList()
            }
        }
    }

    fun postNote() {
        viewModelScope.launch {
            try {
                val user: User = userRepository.user.first()
                val noteText = NoteText(_note.value!!)
                val noteId: Id = MinervaApi.retrofitService.postNote(
                    user.id,
                    _book.value?.isbn!!,
                    noteText
                )
                _notes.value?.add(Note(noteId.id, noteText.note))
                _note.value = ""
            } catch (ex: java.lang.Exception) {
            }
        }
    }

}

class NotesViewModelFactory(private val userRepository: UserRepository, private val book: Book) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(userRepository, book) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }

}
