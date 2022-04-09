package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.howest.defoor.remi.minerva.model.Book
import be.howest.defoor.remi.minerva.model.Note

class BookViewModel : ViewModel() {

    private lateinit var book: Book
    private lateinit var notes: List<Note>

    fun setBook(book: Book) {
        this.book = book
        setNotes()
    }

    fun getBook(): Book {
        return book
    }

    private fun setNotes() {
        notes = getNotesFromMinervaApi()
    }

    fun getNotes(): List<Note> {
        return notes
    }

    private fun getNotesFromMinervaApi(): List<Note> {
        // TODO get notes from minerva api
        return listOf(
            Note(1, "Excited!!!"),
            Note(2, "Perron 9 3/4"),
            Note(3, "Ron and Hermione"),
            Note(4, "Dumbledore"),
            Note(5, "Hogwarts"),
            Note(6, "The super long note to test the behavior of super long notes in my awesome application for taking notes on books."),
            Note(7, "Voldemort")
        )
    }

}
