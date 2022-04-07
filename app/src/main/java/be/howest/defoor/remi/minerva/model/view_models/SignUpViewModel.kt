package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private val _email: MutableLiveData<String> = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _password: MutableLiveData<String> = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    private val _confirmedPassword: MutableLiveData<String> = MutableLiveData<String>()
    val confirmedPassword: LiveData<String>
        get() = _confirmedPassword

    init {
        _email.value = ""
        _password.value = ""
        _confirmedPassword.value = ""
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setConfirmedPassword(confirmedPassword: String) {
        _confirmedPassword.value = confirmedPassword
    }

    fun resetPasswords() {
        _password.value = ""
        _confirmedPassword.value = ""
    }

}
