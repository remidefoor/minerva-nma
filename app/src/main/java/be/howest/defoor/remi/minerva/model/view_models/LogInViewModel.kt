package be.howest.defoor.remi.minerva.model.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.howest.defoor.remi.minerva.model.User
import be.howest.defoor.remi.minerva.network.MinervaApi
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {
    
    private val _email: MutableLiveData<String> = MutableLiveData<String>()
    val email: String
        get() = _email.value!!

    private val _password: MutableLiveData<String> = MutableLiveData<String>()
    val password: String
        get() = _password.value!!

    init {
        _email.value = ""
        _password.value = ""
    }

    fun setEmail(email: String) {
        if(this.email != email) {
            _email.value = email
        }
    }

    fun setPassword(password: String) {
        if (this.password != password) {
            _password.value = password
        }
    }

    fun postLogIn() {
        viewModelScope.launch {
            try {
                val userId: Int = MinervaApi.retrofitService.logIn(User(email, password))
            } catch (ex: Exception) {
                // TODO display api errs
                resetCredentials()
            }
        }
    }

    private fun resetCredentials() {
        _email.value = ""
        _password.value = ""
    }

}
