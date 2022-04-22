package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.Note
import java.lang.IllegalArgumentException

class NotesViewModel(book: Book) : ViewModel() {

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
        _notes.value = listOf(
            Note(1, "Excited!!!"),
            Note(2, "Perron 9 3/4"),
            Note(3, "Ron and Hermione"),
            Note(4, "Dumbledore"),
            Note(5, "Voldemort")
        )
    }

}

class NotesViewModelFactory(private val book: Book) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(book) as T
        }
        throw IllegalArgumentException("Unknown view model class.")
    }

}
