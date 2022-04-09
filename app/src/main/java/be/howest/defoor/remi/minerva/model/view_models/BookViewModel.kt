package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.Note

class BookViewModel : ViewModel() {

    private val _book: MutableLiveData<Book> = MutableLiveData<Book>()
    val book: LiveData<Book>
        get() = _book

    fun setBook(book: Book) {
        _book.value = book
    }

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>>
        get() = _notes

    fun setNotes() {
        _notes.value = getNotes()
    }

    private fun getNotes(): List<Note> {
        TODO("implement")
    }

}
