package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.defoor.remi.minerva.network.minerva.Id
import be.howest.defoor.remi.minerva.network.minerva.Credentials
import be.howest.defoor.remi.minerva.network.minerva.MinervaApi
import kotlinx.coroutines.launch
import java.lang.Exception

class SignUpViewModel : ViewModel() {

    private val _email: MutableLiveData<String> = MutableLiveData<String>()
    val email: String
        get() = _email.value!!

    private val _password: MutableLiveData<String> = MutableLiveData<String>()
    val password: String
        get() = _password.value!!

    private val _confirmedPassword: MutableLiveData<String> = MutableLiveData<String>()
    val confirmedPassword: String
        get() = _confirmedPassword.value!!

    init {
        _email.value = ""
        _password.value = ""
        _confirmedPassword.value = ""
    }

    fun setEmail(email: String) {
        if (!this.email.equals(email)) _email.value = email
    }

    fun setPassword(password: String) {
        if (this.password != password) _password.value = password
    }

    fun setConfirmedPassword(confirmedPassword: String) {
        if (this.confirmedPassword != confirmedPassword) _confirmedPassword.value = confirmedPassword
    }

    fun postUser() {
        if (password == confirmedPassword) {
            viewModelScope.launch {
                try {
                    val id: Id = MinervaApi.retrofitService.postUser(Credentials(email, password))
                } catch (ex: Exception) {
                    // TODO display api errs
                    resetPasswords()
                }
            }
        } else {
            // TODO display err: pwds not equal
            resetPasswords()
        }
    }

    private fun resetPasswords() {
        _password.value = ""
        _confirmedPassword.value = ""
    }

}
