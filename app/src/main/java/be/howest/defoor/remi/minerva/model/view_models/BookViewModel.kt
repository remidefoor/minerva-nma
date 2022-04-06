package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {
    private val _isbn = MutableLiveData<String>()
    val isbn: LiveData<String>
        get() = _isbn

    fun setIsbn(isbn: String) {
        _isbn.value = isbn
    }
}