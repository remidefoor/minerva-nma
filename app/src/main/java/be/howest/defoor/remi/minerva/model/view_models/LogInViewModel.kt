package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogInViewModel : ViewModel() {

    private val _email: MutableLiveData<String> = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _password: MutableLiveData<String> = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    init {
        _email.value = ""
        _password.value = ""
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }
    
}
